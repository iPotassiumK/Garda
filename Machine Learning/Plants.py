#!/usr/bin/env python3

import tensorflow as tf

print('\u2022 Using TensorFlow Version:', tf.__version__)

import os

base_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/"

# Directory for train and validation
train_dir = os.path.join(base_dir, 'Train')
validation_dir = os.path.join(base_dir, 'Validation')

# Directory with our training pictures
train_nightsade_dir = os.path.join(train_dir, 'Black Nightsade')
train_broccoli_dir = os.path.join(train_dir, 'Broccoli')
train_cabbage_dir = os.path.join(train_dir, 'Cabbage')
train_cucumber_dir = os.path.join(train_dir, 'Cucumber')
train_grape_dir = os.path.join(train_dir, 'Grape Vine')
train_pepper_dir = os.path.join(train_dir, 'Pepper')
train_potato_dir = os.path.join(train_dir, 'Potato')
train_tomato_dir = os.path.join(train_dir, 'Tomato')

# Directory with our validation pictures
validation_nightsade_dir = os.path.join(validation_dir, 'Black Nightsade')
validation_broccoli_dir = os.path.join(validation_dir, 'Broccoli')
validation_cabbage_dir = os.path.join(validation_dir, 'Cabbage')
validation_cucumber_dir = os.path.join(validation_dir, 'Cucumber')
validation_grape_dir = os.path.join(validation_dir, 'Grape Vine')
validation_pepper_dir = os.path.join(validation_dir, 'Pepper')
validation_potato_dir = os.path.join(validation_dir, 'Potato')
validation_tomato_dir = os.path.join(validation_dir, 'Tomato')

train_nightsade_fnames = os.listdir(train_nightsade_dir)
train_broccoli_fnames = os.listdir(train_broccoli_dir)
train_cabbage_fnames = os.listdir(train_cabbage_dir)
train_cucumber_fnames = os.listdir(train_cucumber_dir)
train_grape_fnames = os.listdir(train_grape_dir)
train_pepper_fnames = os.listdir(train_pepper_dir)
train_potato_fnames = os.listdir(train_potato_dir)
train_tomato_fnames = os.listdir(train_tomato_dir)

print('Total training Black Nightsade images :', len(os.listdir(train_nightsade_dir)))
print('Total training Broccoli images :', len(os.listdir(train_broccoli_dir)))
print('Total training Cabbage images :', len(os.listdir(train_cabbage_dir)))
print('Total training Cucumber images :', len(os.listdir(train_cucumber_dir)))
print('Total training Grape Vine images :', len(os.listdir(train_grape_dir)))
print('Total training Pepper images :', len(os.listdir(train_pepper_dir)))
print('Total training Potato images :', len(os.listdir(train_potato_dir)))
print('Total training Tomato images :', len(os.listdir(train_tomato_dir)))

print('Total validation Black Nightsade images :', len(os.listdir(validation_nightsade_dir)))
print('Total validation Broccoli images :', len(os.listdir(validation_broccoli_dir)))
print('Total validation Cabbage images :', len(os.listdir(validation_cabbage_dir)))
print('Total validation Cucumber images :', len(os.listdir(validation_cucumber_dir)))
print('Total validation Grape Vine images :', len(os.listdir(validation_grape_dir)))
print('Total validation Pepper images :', len(os.listdir(validation_pepper_dir)))
print('Total validation Potato images :', len(os.listdir(validation_potato_dir)))
print('Total validation Tomato images :', len(os.listdir(validation_tomato_dir)))

import matplotlib.image as mpimg
import matplotlib.pyplot as plt

# Parameters for our graph; we'll output images in a 4x4 configuration
nrows = 8
ncols = 4

pic_index = 0 # Index for iterating over images

# Set up matplotlib fig, and size it to fit 4x4 pics
fig = plt.gcf()
fig.set_size_inches(ncols*4, nrows*4)

pic_index+=4

next_nightsade_pix = [os.path.join(train_nightsade_dir, fname) 
                for fname in train_nightsade_fnames[ pic_index-4:pic_index] 
               ]

next_broccoli_pix = [os.path.join(train_broccoli_dir, fname) 
                for fname in train_broccoli_fnames[ pic_index-4:pic_index] 
               ]
               
next_cabbage_pix = [os.path.join(train_cabbage_dir, fname) 
                for fname in train_cabbage_fnames[ pic_index-4:pic_index] 
               ]

next_cucumber_pix = [os.path.join(train_cucumber_dir, fname) 
                for fname in train_cucumber_fnames[ pic_index-4:pic_index] 
               ]

next_grape_pix = [os.path.join(train_grape_dir, fname) 
                for fname in train_grape_fnames[ pic_index-4:pic_index] 
               ]

next_pepper_pix = [os.path.join(train_pepper_dir, fname) 
                for fname in train_pepper_fnames[ pic_index-4:pic_index] 
               ]

next_potato_pix = [os.path.join(train_potato_dir, fname) 
                for fname in train_potato_fnames[ pic_index-4:pic_index] 
               ]
               
next_tomato_pix = [os.path.join(train_tomato_dir, fname) 
                for fname in train_tomato_fnames[ pic_index-4:pic_index] 
               ]
               
for i, img_path in enumerate(next_nightsade_pix+next_broccoli_pix+next_cabbage_pix+next_cucumber_pix+next_grape_pix+next_pepper_pix+next_potato_pix+next_tomato_pix):
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
