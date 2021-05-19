#!/usr/bin/env python3

import os

os.environ["TF_CPP_MIN_LOG_LEVEL"] = "2"

import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers

model = keras.models.load_model("Plants.h5")

#model.summary()

from PIL import Image
import random
import numpy as np

size = 150

# Prediction using different images for training and validating

class_list = ["Black Nightshade", "Broccoli", "Cabbage", "Cucumber", "Grape Vine", "Pepper", "Potato", "Tomato"]
test_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/Test/"
fresh_pick = "" #directly the source file

random_class = random.choice(class_list)
random_image = random.choice(os.listdir(os.path.join(test_dir, random_class)))
random_image_path = os.path.join(test_dir, random_class, random_image)
#print(random_image_path)

#Using random image 
#"""
image = keras.preprocessing.image.load_img(random_image_path, target_size=(size, size))
im = Image.open(random_image_path)
print(random_image_path)
#"""

#Using fresh pick image
"""
image = keras.preprocessing.image.load_img(fresh_pick, target_size=(size, size))
im = Image.open(fresh_pick)
print(fresh_pick)
"""

input_arr = keras.preprocessing.image.img_to_array(image)
input_arr = np.array([input_arr])  # Convert single image to a batch.

im.thumbnail((size, size), Image.ANTIALIAS)
im.show()

numpy_prediction = model.predict(input_arr)
prediction_list = numpy_prediction.tolist()

#print(numpy_prediction)

for i in range(len(class_list)):
    if prediction_list[0][i] == 1:
        print("I think I see a " + class_list[i] + " plants!")
