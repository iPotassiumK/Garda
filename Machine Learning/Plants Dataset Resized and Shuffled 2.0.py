#!/usr/bin/env python3

import os
import random
import shutil
import math
from PIL import Image

def split_shuffle_resize_data(SOURCE1, SOURCE2, TRAINING, VALIDATION, TEST):
    #Quantity set
    size = 150, 150
    counter1 = 0
    counter2 = 0
    training_quantity = [250, 25] 
    validation_quantity = [50, 0]
    test_quantity = [21, 4]
    
    #Random picker
    random_pick_source1 = random.sample(os.listdir(SOURCE1), len(os.listdir(SOURCE1)))
    random_pick_source2 = random.sample(os.listdir(SOURCE2), len(os.listdir(SOURCE2)))

    #Copying files
    for i in range(training_quantity[0] + validation_quantity[0] + test_quantity[0]):
        if i < training_quantity[0]:
            im = Image.open(SOURCE1 + "/" + random_pick_source1[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
            except OSError:
                print("Failed to resize image at : {}".format(SOURCE1 + "/" + random_pick_source1[i]))
            im.save(TRAINING + "/" + random_pick_source1[i], "JPEG")
            #copyfile(SOURCE1 + random_pick_source1[i], TRAINING + random_pick_source1[i])
            #print("Copied " + random_pick_source1[i] + " to " + TRAINING)
            #pass
        elif i < (validation_quantity[0] + training_quantity[0]):
            im = Image.open(SOURCE1 + "/" + random_pick_source1[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
            except OSError:
                print("Failed to resize image at : {}".format(SOURCE1 + "/" + random_pick_source1[i]))
            im.save(VALIDATION + "/" + random_pick_source1[i], "JPEG")
            #copyfile(SOURCE1 + random_pick_source1[i], VALIDATION + random_pick_source1[i])
            #print("Copied " + random_pick_source1[i] + " to " + VALIDATION)
            #pass
        else:
            im = Image.open(SOURCE1 + "/" + random_pick_source1[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
            except OSError:
                print("Failed to resize image at : {}".format(SOURCE1 + "/" + random_pick_source1[i]))
            im.save(TEST + "/" + random_pick_source1[i], "JPEG")
            #copyfile(SOURCE1 + random_pick_source1[i], TEST + random_pick_source1[i])
            #print("Copied " + random_pick_source1[i] + " to " + TEST)
            #pass
        
        counter1+=1
    
    for i in range(training_quantity[1] + validation_quantity[1] + test_quantity[1]):
        if i < training_quantity[1]:
            im = Image.open(SOURCE2 + "/" + random_pick_source2[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
            except OSError:
                print("Failed to resize image at : {}".format(SOURCE2 + "/" + random_pick_source2[i]))
            im.save(TRAINING + "/" + random_pick_source2[i], "JPEG")
            #copyfile(SOURCE2 + random_pick_source2[i], TRAINING + random_pick_source2[i])
            #print("Copied " + random_pick_source2[i] + " to " + TRAINING)
            #pass
        elif i < (validation_quantity[1] + training_quantity[1]):
            im = Image.open(SOURCE2 + "/" + random_pick_source2[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
            except OSError:
                print("Failed to resize image at : {}".format(SOURCE2 + "/" + random_pick_source2[i]))
            im.save(VALIDATION + "/" + random_pick_source2[i], "JPEG")
            #copyfile(SOURCE1 + random_pick_source2[i], VALIDATION + random_pick_source2[i])
            #print("Copied " + random_pick_source2[i] + " to " + VALIDATION)
            #pass
        else:
            im = Image.open(SOURCE2 + "/" + random_pick_source2[i])
            try:
                im.thumbnail(size, Image.ANTIALIAS)
            except OSError:
                print("Failed to resize image at : {}".format(SOURCE2 + "/" + random_pick_source2[i]))
            im.save(TEST + "/" + random_pick_source2[i], "JPEG")
            #copyfile(SOURCE2 + random_pick_source2[i], TEST + random_pick_source2[i])
            #print("Copied " + random_pick_source2[i] + " to " + TEST)
            #pass
        
        counter2+=1

    print("Copied {} files".format(counter1+counter2))

base_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/"
source_dir = "/run/media/potassium/Local-Disk_E/Homework/6th Semester/Bangkit 2021/Capstone/Dataset/"
google_dir = os.path.join(source_dir, 'Google Images')
eden_dir = os.path.join(source_dir, "Eden Library Images")

# Directory for train and validation
train_dir = os.path.join(base_dir, 'Train')
validation_dir = os.path.join(base_dir, 'Validation')
test_dir = os.path.join(base_dir, 'Test')

class_list = ["Broccoli", "Cabbage", "Carrot", "Chilli", "Cucumber", "Grape Vine", "Onion", "Potato", "Spinach", "Tomato"]

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
    # Index 0 is Train directory, index 1 is Validation directory, , index 2 is Test directory, index 3 is Google directory, index 4 is Eden directory
    dictionary_class_dir[model_class] = []
    dictionary_class_dir[model_class].append(os.path.join(train_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(validation_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(test_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(google_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(eden_dir, model_class))
    split_shuffle_resize_data(dictionary_class_dir[model_class][3], dictionary_class_dir[model_class][4], dictionary_class_dir[model_class][0], dictionary_class_dir[model_class][1], dictionary_class_dir[model_class][2])

#print(dictionary_class_dir["Broccoli"][0])
#print(dictionary_class_dir["Cabbage"][1])
#print(dictionary_class_dir["Pepper"][2])
