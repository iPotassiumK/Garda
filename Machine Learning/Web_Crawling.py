#!/usr/bin/env python3
from urllib.request import urlopen
from bs4 import BeautifulSoup as soup
import re
import pandas as pd

plant = urlopen('https://en.wikipedia.org/wiki/Potato') #change Potato with Tomato, Carrot, Spinach, Chili Pepper, Onion, Vitis/Grape, Cabbage, Broccoli, Cucumber
bsobj = soup(plant.read(), features = 'lxml')

data = []
for link in bsobj.find('div', {'id':'bodyContent'}).findAll('a', href = re.compile('^(https://)((?!:).)*$')):
  if 'href' in link.attrs:
#    print(link.attrs['href'])
    data.append({'url':link.attrs['href']})

df = pd.DataFrame(data[5:15])
df.to_csv('potato.csv', index = 'False', encoding = 'utf-8') #change file name with corresponding plant name above

