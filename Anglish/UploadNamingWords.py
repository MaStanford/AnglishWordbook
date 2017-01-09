__author__ = 'm.stanford'

__author__ = 'm.stanford'

import string

import json, httplib

nameheader = 'Naming Words'
pageheader = 'Page'
maleheader = 'Male Names'
femaleheader = 'Female Names'
flushline = ' . '
lines = []

wordType = ['NameWord', 'FemaleName', 'MaleName']

section = wordType[0]

def buildNameDef(processedWord):

    word = {}

    word['O.E. Name'] = processedWord['oe']
    word['N.E. Name'] = processedWord['ne']
    word['outspeak'] = processedWord['pronounce']
    word['meaning'] = processedWord['meaning']

    return word


def addWord(wordDef, type):

    connection = httplib.HTTPSConnection('https://anglishwordbook.herokuapp.com/', 443)
    connection.connect()
    connection.request('POST', '/1/classes/' + type, json.dumps(wordDef), {
       "X-Parse-Application-Id": "ApuxkukQC9mFuLIdIjG3qC27ms5kZ4XZbopxUohp",
       "X-Parse-REST-API-Key": "N6r8fxsert4JrvaGMcavcTtaYPed6Vl9qNDE8mqb",
       "Content-Type": "application/json"
    })
    result = json.loads(connection.getresponse().read())
    print result


def processNameWords(lines):
    pieces = []
    words = {}
    ne = ''
    oe = ''
    meaning = ''
    pronounce = ''

    if len(lines) > 2:
        print 'Lines: ' + str(lines)
        pieces = lines[0].split('.')[1].split(' ')
        oe = pieces[1]
        pronounce = pieces[2]
        meaning += str(pieces[3:])
        pieces = lines[1].split(' ')
        ne = pieces[0]
        meaning += str(pieces[1:])
    else:
        print 'Lines: ' + str(lines)
        pieces = lines[0].split('.')[1].split(' ')
        oe = pieces[1]
        pronounce = pieces[2]
        meaning += str(pieces[3:])
        ne = lines[1]

    words['ne'] = ne
    words['oe'] = oe
    words['meaning'] = meaning
    words['pronounce'] = pronounce

    print 'Word: ' + str(words)

    addWord(words, wordType[0])

def processFemaleWords(lines):
    pieces = []
    words = {}
    name = ''
    breakdown = ''
    explain = ''

    if not lines:
        return

    if len(lines) > 2:
        print 'Lines: ' + str(lines)
        pieces = lines[0].split('.')[1].split(' ')
        name = pieces[1]
        breakdown = pieces[2:]
        explain += lines[1]
        explain += lines[2]
    else:
        print 'Lines: ' + str(lines)
        pieces = lines[0].split('.')[1].split(' ')
        name = pieces[1]
        breakdown = pieces[2:]
        explain += lines[1]

    words['name'] = name
    words['breakdown'] = breakdown
    words['explain'] = explain

    print 'Word: ' + str(words)

    addWord(words, wordType[1])

def processMaleWords(lines):
    pieces = []
    words = {}
    name = ''
    breakdown = ''
    explain = ''

    if not lines:
        return

    if len(lines) > 2:
        print 'Lines: ' + str(lines)
        pieces = lines[0].split('.')[1].split(' ')
        name = pieces[1]
        breakdown = pieces[2:]
        explain += lines[1]
        explain += lines[2]
    else:
        print 'Lines: ' + str(lines)
        pieces = lines[0].split('.')[1].split(' ')
        name = pieces[1]
        breakdown = pieces[2:]
        explain += lines[1]

    words['name'] = name
    words['breakdown'] = breakdown
    words['explain'] = explain

    print 'Word: ' + str(words)

    addWord(words, wordType[2])




def isNumInLine(line):
    if line.split('.')[0].isdigit():
        return True
    return False


f = open('./NamingWords.txt', 'r')
i = 0
for line in f:
    if pageheader in line:
        continue

    if nameheader in line:
        section = wordType[0]
        continue

    if maleheader in line:
        section = wordType[2]
        continue

    if femaleheader in line:
        section = wordType[1]
        continue

    if section is wordType[0]:
        if isNumInLine(line):
            if i > 0:
                processNameWords(lines)
                lines = []
                i = 0
                lines.append(line)
            else:
                lines.append(line)
        else:
            lines.append(line)

    if section is wordType[1]:
        if isNumInLine(line):
            if i > 0:
                processFemaleWords(lines)
                lines = []
                i = 0
                lines.append(line)
            else:
                lines.append(line)
        else:
            lines.append(line)

    if section is wordType[2]:
        if isNumInLine(line):
            if i > 0:
                processMaleWords(lines)
                lines = []
                i = 0
                lines.append(line)
            else:
                lines.append(line)
        else:
            lines.append(line)

    i += 1