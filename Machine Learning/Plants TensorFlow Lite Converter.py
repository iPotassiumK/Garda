#!/usr/bin/env python3

import os
os.environ["TF_CPP_MIN_LOG_LEVEL"] = "2"

import tensorflow as tf
import pathlib

# Convert the model.
source_file_name = "Plants Transfer Learning.h5"
destination_file_name = "Plants.tflite"

model = tf.keras.models.load_model(source_file_name)

model.summary()

print("Converting...")
#For default saved model (Folder with PB file, assets, and variables)
"""
converter = tf.lite.TFLiteConverter.from_saved_model(model)
tflite_model = converter.convert()

tflite_model_file = pathlib.Path('Plants.tflite')
tflite_model_file.write_bytes(tflite_model)

# Load TFLite model and allocate tensors.
interpreter = tf.lite.Interpreter(model_content=tflite_model)
interpreter.allocate_tensors()
"""

#For saved model with format HD5
converter = tf.lite.TFLiteConverter.from_keras_model(model)
tfmodel = converter.convert()
open (destination_file_name , "wb") .write(tfmodel)

print("File saved as TFlite!")
