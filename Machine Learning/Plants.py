#!/usr/bin/env python3

import tensorflow as tf

print('\u2022 Using TensorFlow Version:', tf.__version__)

import os

base_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/"

# Directory for train and validation
train_dir = os.path.join(base_dir, 'Train')
validation_dir = os.path.join(base_dir, 'Validation')

class_list = ["Black Nightshade", "Broccoli", "Cabbage", "Cucumber", "Grape Vine", "Pepper", "Potato", "Tomato"]

dictionary_class_dir = {}

for model_class in class_list:
    # Index 0 is Train directory, index 1 is Validation directory, index 2 is Train list
    dictionary_class_dir[model_class] = []
    dictionary_class_dir[model_class].append(os.path.join(train_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(validation_dir, model_class))
    dictionary_class_dir[model_class].append(os.listdir(dictionary_class_dir[model_class][0]))
    print("Total training {} images : {}".format(model_class, len(dictionary_class_dir[model_class][2])))
    print("Total validating {} images : {}".format(model_class, len(os.listdir(dictionary_class_dir[model_class][1]))))
    print("")
    
#print(dictionary_class_dir["Broccoli"][0])
#print(dictionary_class_dir["Cabbage"][1])
#print(dictionary_class_dir["Broccoli"][2][:5])

import matplotlib.image as mpimg
import matplotlib.pyplot as plt
import random

# Parameters for our graph; we'll output images in a 4x4 configuration
nrows = len(class_list)
ncols = 4

pic_index = 4 # Index for iterating over images

# Set up matplotlib fig, and size it to fit 4x4 pics
fig = plt.gcf()
fig.set_size_inches(ncols*4, nrows*4)

next_pix = []
#all_pix = []

for model_class in class_list:
    for i in range(pic_index):
        next_pix.append(os.path.join(dictionary_class_dir[model_class][0], random.choice(dictionary_class_dir[model_class][2])))

#print(next_pix)   

"""
for i in range(len(next_pix)):
    for picture_path in next_pix[i]:
        all_pix.append(picture_path)

#print(all_pix)
"""

for i, img_path in enumerate(next_pix):
    # Set up subplot; subplot indices start at 1
    sp = plt.subplot(nrows, ncols, i + 1)
    sp.axis('Off') # Don't show axes (or gridlines)
    
    img = mpimg.imread(img_path)
    plt.imshow(img)

plt.show()

import keras_preprocessing
from keras_preprocessing import image
from keras_preprocessing.image import ImageDataGenerator

training_datagen = ImageDataGenerator(
      rescale = 1./255,
	  rotation_range=40,
      width_shift_range=0.2,
      height_shift_range=0.2,
      shear_range=0.2,
      zoom_range=0.2,
      horizontal_flip=True,
      fill_mode='nearest')

validation_datagen = ImageDataGenerator(rescale = 1./255)


train_generator = training_datagen.flow_from_directory(
	train_dir,
	target_size=(150,150),
	class_mode='categorical',
	batch_size=49
)

validation_generator = validation_datagen.flow_from_directory(
	validation_dir,
	target_size=(150,150),
	class_mode='categorical',
	batch_size=55
)

model = tf.keras.models.Sequential([
    # Note the input shape is the desired size of the image 150x150 with 3 bytes color
    # This is the first convolution
    tf.keras.layers.Conv2D(64, (3,3), activation='relu', input_shape=(150, 150, 3)),
    tf.keras.layers.MaxPooling2D(2, 2),
    # The second convolution
    tf.keras.layers.Conv2D(64, (3,3), activation='relu'),
    tf.keras.layers.MaxPooling2D(2,2),
    # The third convolution
    tf.keras.layers.Conv2D(128, (3,3), activation='relu'),
    tf.keras.layers.MaxPooling2D(2,2),
    # The fourth convolution
    tf.keras.layers.Conv2D(128, (3,3), activation='relu'),
    tf.keras.layers.MaxPooling2D(2,2),
    # Flatten the results to feed into a DNN
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dropout(0.5),
    # 512 neuron hidden layer
    tf.keras.layers.Dense(512, activation='relu'),
    tf.keras.layers.Dense(8, activation='softmax')
])

model.summary()

model.compile(loss = 'categorical_crossentropy', optimizer='rmsprop', metrics=['accuracy'])


history = model.fit(train_generator, epochs=25, steps_per_epoch=40, validation_data = validation_generator, verbose = 1, validation_steps=4)

model.save("Plants.h5")

#PART 4 Graphing the accuracy and loss
import matplotlib.pyplot as plt
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

plt.show()
