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

# Prediction using the same images for training and validating
class_list = ["Black Nightsade", "Broccoli", "Cabbage", "Cucumber", "Grape Vine", "Pepper", "Potato", "Tomato"]
source_dir = "/run/media/potassium/Local-Disk_E/Homework/6th Semester/Bangkit 2021/Capstone/Dataset/"

random_class = random.choice(class_list)
random_image = random.choice(os.listdir(os.path.join(source_dir, random_class)))
random_image_path = os.path.join(source_dir, random_class, random_image)
#print(random_image_path)

image = keras.preprocessing.image.load_img(random_image_path, target_size=(size, size))
input_arr = keras.preprocessing.image.img_to_array(image)
input_arr = np.array([input_arr])  # Convert single image to a batch.

im = Image.open(random_image_path)
im.thumbnail((size, size), Image.ANTIALIAS)
im.show()

numpy_prediction = model.predict(input_arr)
prediction_list = numpy_prediction.tolist()
prediction = ""

print(random_image_path)
print(numpy_prediction)

for i in range(len(class_list)):
    if prediction_list[0][i] == 1:
        print(class_list[i])
