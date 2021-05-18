#!/usr/bin/env python3

import os

base_dir = "/home/potassium/Documents/Bangkit Capstone 2021/Dataset/"

# Directory for train and validation
train_dir = os.path.join(base_dir, 'Train')
validation_dir = os.path.join(base_dir, 'Validation')
test_dir = os.path.join(base_dir, 'Test')

class_list = ["Black Nightshade", "Broccoli", "Cabbage", "Cucumber", "Grape Vine", "Pepper", "Potato", "Tomato"]

dictionary_class_dir = {}

for model_class in class_list:
    # Index 0 is Train directory, index 1 is Validation directory, , index 2 is Test directory
    dictionary_class_dir[model_class] = []
    dictionary_class_dir[model_class].append(os.path.join(train_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(validation_dir, model_class))
    dictionary_class_dir[model_class].append(os.path.join(test_dir, model_class))
    print(dictionary_class_dir[model_class][0])
    for i in range(3):
        counter = 0
        for image in os.listdir(dictionary_class_dir[model_class][i]):
            os.remove(dictionary_class_dir[model_class][i] + "/" + image)
            counter+=1
        print("Deleted {} images in {}".format(counter, dictionary_class_dir[model_class][i]))
    print("")
