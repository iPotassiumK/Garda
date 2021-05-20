#!/usr/bin/env python3

import os
import random
import shutil
import math
from PIL import Image

def split_and_resize_data(CLASS, SOURCE, TRAINING, VALIDATION, TEST, SPLIT_SIZE):
    #Quantity set
    size = 150, 150
    counter = 0
    training_quantity = math.floor(len(os.listdir(SOURCE)) * SPLIT_SIZE)
    validation_quantity = math.floor((len(os.listdir(SOURCE)) - training_quantity)* SPLIT_SIZE)
    test_quantity = len(os.listdir(SOURCE)) - training_quantity - validation_quantity
    
    #Random picker
    random_pick = random.sample(os.listdir(SOURCE), len(os.listdir(SOURCE)))

    #Copying files
    for i in range(len(random_pick)):
    #for i in range(training_quantity + validation_quantity):
        if i < training_quantity:
            im = Image.open(SOURCE + "/" + random_pick[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
                im.save(TRAINING + "/" + random_pick[i], "JPEG")
            except OSError:
                print("Failed to modify image at : {}".format(SOURCE + "/" + random_pick[i]))
            #copyfile(SOURCE + random_pick[i], TRAINING + random_pick[i])
            #print("Copied " + random_pick[i] + " to " + TRAINING)
            #pass
        elif i < (validation_quantity + training_quantity):
            im = Image.open(SOURCE + "/" + random_pick[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
                im.save(VALIDATION + "/" + random_pick[i], "JPEG")
            except OSError:
                print("Failed to modify image at : {}".format(SOURCE + "/" + random_pick[i]))
            #copyfile(SOURCE + random_pick[i], VALIDATION + random_pick[i])
            #print("Copied " + random_pick[i] + " to " + VALIDATION)
            #pass
        else:
            im = Image.open(SOURCE + "/" + random_pick[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
                im.save(TEST + "/" + random_pick[i], "JPEG")
            except OSError:
                print("Failed to modify image at : {}".format(SOURCE + "/" + random_pick[i]))
            #copyfile(SOURCE + random_pick[i], TEST + random_pick[i])
            #print("Copied " + random_pick[i] + " to " + TEST)
            #pass
            
        counter+=1

    print("Copied {} files with {} training, {} validating, {} testing for class {}".format(counter, training_quantity, validation_quantity, test_quantity, CLASS))

split_size = .8

base_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/"
source_dir = "/run/media/potassium/Local-Disk_E/Homework/6th Semester/Bangkit 2021/Capstone/Dataset/Google Images/"

# Directory for train and validation
train_dir = os.path.join(base_dir, 'Train')
validation_dir = os.path.join(base_dir, 'Validation')
test_dir = os.path.join(base_dir, 'Test')

class_list = ["Black Nightshade", "Broccoli", "Cabbage", "Cucumber", "Grape Vine", "Pepper", "Potato", "Tomato"]

dictionary_class_dir = {}

try:
    folder_list = ['Train', 'Validation', 'Test']
    
    #Directory making
    #os.mkdir(base_dir)
    #print("Created directory at" + " " + base_dir)
    for i in range(len(folder_list)):
        os.mkdir(base_dir + folder_list[i])
        print("Created directory at" + " " + base_dir + folder_list[i])
        for j in range(len(class_list)):
            os.mkdir(base_dir + folder_list[i] + "/" + class_list[j])
            print("Created directory at" + " " + base_dir + folder_list[i] + "/" + class_list[j])

except OSError:
    print("Directory already exist!")

for model_class in class_list:
    # Index 0 is Train directory, index 1 is Validation directory, index 2 is Test directory, index 3 is Source directory
    dictionary_class_dir[model_class] = []
    dictionary_class_dir[model_class].append(os.path.join(train_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(validation_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(test_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(source_dir, model_class))
    split_and_resize_data(model_class, dictionary_class_dir[model_class][3], dictionary_class_dir[model_class][0], dictionary_class_dir[model_class][1], dictionary_class_dir[model_class][2], split_size)

#print(dictionary_class_dir["Broccoli"][0])
#print(dictionary_class_dir["Cabbage"][1])
#print(dictionary_class_dir["Pepper"][2])
