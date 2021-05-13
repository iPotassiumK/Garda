#!/usr/bin/env python3

import os
import random
import shutil
import math
from PIL import Image

base_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/"
source_dir = "/run/media/potassium/Local-Disk_E/Homework/6th Semester/Bangkit 2021/Capstone/Dataset/"

# Directory for source
source_nightsade_dir = os.path.join(source_dir, 'Black Nightsade')
source_broccoli_dir = os.path.join(source_dir, 'Broccoli')
source_cabbage_dir = os.path.join(source_dir, 'Cabbage')
source_cucumber_dir = os.path.join(source_dir, 'Cucumber')
source_grape_dir = os.path.join(source_dir, 'Grape Vine')
source_pepper_dir = os.path.join(source_dir, 'Pepper')
source_potato_dir = os.path.join(source_dir, 'Potato')
source_tomato_dir = os.path.join(source_dir, 'Tomato')

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

split_size = .9

def split_and_resize_data(SOURCE, TRAINING, VALIDATION, SPLIT_SIZE):
    #Quantity set
    size = 150, 150
    counter = 0
    training_quantity = math.floor(len(os.listdir(SOURCE)) * SPLIT_SIZE)
    validation_quantity = len(os.listdir(SOURCE)) - training_quantity
    
    #Random picker
    random_pick = random.sample(os.listdir(SOURCE), len(os.listdir(SOURCE)))

    #Copying files
    #for i in range(len(random_pick)):
    for i in range(training_quantity + validation_quantity):
        if i < training_quantity:
            im = Image.open(SOURCE + "/" + random_pick[i])
            im.thumbnail(size, Image.ANTIALIAS)
            im.save(TRAINING + "/" + random_pick[i], "JPEG")
            #copyfile(SOURCE + random_pick[i], TRAINING + random_pick[i])
            #print("Copied " + random_pick[i] + " to " + TRAINING)
        else:
            im = Image.open(SOURCE + "/" + random_pick[i])
            im.thumbnail(size, Image.ANTIALIAS)
            im.save(VALIDATION + "/" + random_pick[i], "JPEG")
            #copyfile(SOURCE + random_pick[i], VALIDATION + random_pick[i])
            #print("Copied " + random_pick[i] + " to " + VALIDATION)
        counter+=1

    print("Copied {} files".format(counter))

split_and_resize_data(source_nightsade_dir, train_nightsade_dir, validation_nightsade_dir, split_size)
split_and_resize_data(source_broccoli_dir, train_broccoli_dir, validation_broccoli_dir, split_size)
split_and_resize_data(source_cabbage_dir, train_cabbage_dir, validation_cabbage_dir, split_size)
split_and_resize_data(source_cucumber_dir, train_cucumber_dir, validation_cucumber_dir, split_size)
split_and_resize_data(source_grape_dir, train_grape_dir, validation_grape_dir, split_size)
split_and_resize_data(source_pepper_dir, train_pepper_dir, validation_pepper_dir, split_size)
split_and_resize_data(source_potato_dir, train_potato_dir, validation_potato_dir, split_size)
split_and_resize_data(source_tomato_dir, train_tomato_dir, validation_tomato_dir, split_size)

