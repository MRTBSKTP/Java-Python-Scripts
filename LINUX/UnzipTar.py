#!/usr/bin/python
#----------------------------------------
# For LINUX
# This is a script for unzipping tar
# archive file that user inputs. Script
# must be in same location with file.
#----------------------------------------
# Mert Beşiktepe ITU 2019

# imports
import os
import re
import subprocess
#----------------------------------------

# Functions
# get list of files in cwd
def getFiles ():
    for root, directories, files in os.walk("."):
        # we only need names of files in CWD
        return list(files)

# finding the file that user wants
def getFileName (files, name):
    # file : files list of cwd
    # name : file name user inputted
    # search caseinsentively
    #-----------------------------------
    candidates = list()
    for file in files:
        result = re.search(name,file, re.IGNORECASE)
        # if a matching occurs
        if result:
            # append file name to candidate list
            candidates.append(file)
    else:
        # if more than 1 matches have been found
        if len(candidates) > 1:
            print(f"\nThese are the matches have been found based on user query :{name}")
            print("{:<60}".format("File Name :"), "{:>2}".format("Index :"))
            print("{:-<70}".format(""))
            for index, candidate in enumerate(candidates):
                print("{:<60}".format(candidate), "{:>2}".format(index))
            else:
                while True:
                    try:
                        print("{:-<70}".format(""))
                        indexInput = int(input("Please enter an index for demanded file : "))
                        # retrieve the fileName with user entered index...                
                        return candidates[indexInput]
                    except :
                        print("Invalid Entry....")
                        continue
        # if only 1 match
        else:
            return candidates[0]

# function returns destinaton folder name
def createDestination():
    destination = input("Destination folder to be created : ")
    if not destination:
        # recurse until an input
        getDestination()
    else:
        try:
            os.mkdir(destination)
        except Exception as e:
            print(e)
            # invalid folder name
            destination = createDestination()
    # return the folder name only
    return destination
    
#----------------------------------------
    
# Driver Code
# print files
fileNames = getFiles()
print("Present Files in Directory:","{:-<70}".format(""),*fileNames,"{:-<70}".format(""),sep="\n")

# user input file name or name portion
inputName = input("\nPlease enter full file name or a portion of it for unzipping : ")

# getting the valid matches for file names user entered
file = getFileName(fileNames, inputName)

# creating destination folder
destination = createDestination()

# We will create a subprocess ;
print("\nUnzipped File :",file, "\nTo Folder :",destination)
# constructing command:
tarCommand = "tar" + " " + "-xzvf" + " " + file + " " + "-C"  + " " + f"{destination}"
                        # -x : extract
                        # -z : gzip compression
                        # -v : verbose
                        # -f : file name
# spawning the tar subprocess (only accepts a list as input process args)
subprocess.call(tarCommand.split())
 
