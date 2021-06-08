#!/usr/bin/env python3
from urllib.request import urlopen
from bs4 import BeautifulSoup as soup
import re
import pandas as pd

plant = urlopen('https://en.wikipedia.org/wiki/Strawberry') #change Potato with other plants
bsobj = soup(plant.read(), features = 'lxml')

data = []
for link in bsobj.find('div', {'id':'bodyContent'}).findAll('a', href = re.compile('^(https://doi)((?!:).)*$')):
  if 'href' in link.attrs:
#    print(link.attrs['href'])
    data.append({'url':link.attrs['href']})

df = pd.DataFrame(data)
df.to_csv('strawberry.csv', index = 'False', encoding = 'utf-8') #change file name with corresponding plant name above

