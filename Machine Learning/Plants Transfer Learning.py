#!/usr/bin/env python3

import os
os.environ["TF_CPP_MIN_LOG_LEVEL"] = "2"

import tensorflow as tf

print('\u2022 Using TensorFlow Version:', tf.__version__)

old_model = tf.keras.models.load_model("Plants.h5")
#old_model.summary()

for layer in old_model.layers[:-1]:
    layer.trainable = False
    
base_input = old_model.layers[0].input
base_output = old_model.layers[-1].output

model = tf.keras.Model(inputs=base_input, outputs=base_output)
model.summary()

base_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/"

# Directory for train and validation
train_dir = os.path.join(base_dir, 'Train')
validation_dir = os.path.join(base_dir, 'Validation')

class_list = ["Broccoli", "Cabbage", "Carrot", "Chili", "Cucumber", "Grape Vine", "Onion", "Potato", "Spinach", "Tomato"]

dictionary_class_dir = {}

for model_class in class_list:
    # Index 0 is Train directory, index 1 is Validation directory, index 2 is Train list
    dictionary_class_dir[model_class] = []
    dictionary_class_dir[model_class].append(os.path.join(train_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(validation_dir, model_class))
    dictionary_class_dir[model_class].append(os.listdir(dictionary_class_dir[model_class][0]))
    print("Total re-training {} images : {}".format(model_class, len(dictionary_class_dir[model_class][2])))
    print("Total re-validating {} images : {}".format(model_class, len(os.listdir(dictionary_class_dir[model_class][1]))))
    print("")

import keras_preprocessing
from keras_preprocessing import image
from keras_preprocessing.image import ImageDataGenerator

class myCallback(tf.keras.callbacks.Callback):
  def on_epoch_end(self, epoch, logs={}):
    if(logs.get('val_accuracy')>0.85):
      print("\nReached 85% validation accuracy so cancelling training!")
      self.model.stop_training = True
      
callbacks = myCallback()

training_datagen = ImageDataGenerator(
      rescale = 1./255,
	  rotation_range=90,
      width_shift_range=0.2,
      height_shift_range=0.2,
      brightness_range=(0.1, 0.9),
      shear_range=45.0,
      zoom_range=[0.5, 1.5],
      channel_shift_range=150.0,
      horizontal_flip=True,
      vertical_flip=True,
      fill_mode='reflect')

validation_datagen = ImageDataGenerator(rescale = 1./255)

#Depends on total training images, example 2200 images = 44 x 50 so batch 44 with 50 steps
batch_size_train = 50
steps_train = 29

#Depends on total validating images, example 400 images = 50 x 8 so batch 50 with 8 steps
batch_size_validation = 48
steps_validation = 6

train_generator = training_datagen.flow_from_directory(
	train_dir,
	target_size=(150,150),
	class_mode='categorical',
	batch_size= batch_size_train 
)

validation_generator = validation_datagen.flow_from_directory(
	validation_dir,
	target_size=(150,150),
	class_mode='categorical',
	batch_size= batch_size_validation
)

lr_schedule = tf.keras.optimizers.schedules.ExponentialDecay(
    initial_learning_rate=1e-3,
    decay_steps=100,
    decay_rate=0.9)
    
optimizer = tf.keras.optimizers.SGD(learning_rate=lr_schedule)

model.compile(loss = 'categorical_crossentropy', optimizer=optimizer, metrics=['accuracy'])

try:
    history = model.fit(train_generator, epochs=100, steps_per_epoch=steps_train, validation_data = validation_generator, verbose = 1, validation_steps=steps_validation, callbacks=[callbacks])
except KeyboardInterrupt:
    print("")

model.save("Plants Transfer Learning.h5")
print("Output saved!")

#PART 4 Graphing the accuracy and loss

import matplotlib.pyplot as plt

try:
    acc = history.history['accuracy']
    val_acc = history.history['val_accuracy']
    loss = history.history['loss']
    val_loss = history.history['val_loss']

    epochs = range(len(acc))

    plt.plot(epochs, acc, 'r', label='Training accuracy')
    plt.plot(epochs, val_acc, 'b', label='Validation accuracy')
    plt.title('Training and validation accuracy')
    plt.legend(loc=0)
    plt.figure()

    plt.plot(epochs, loss, 'r', label='Training loss')
    plt.plot(epochs, val_loss, 'b', label='Validation loss')
    plt.title('Training and validation loss')
    plt.legend(loc=0)
    plt.figure()

    plt.show()
    
except NameError:
    pass
