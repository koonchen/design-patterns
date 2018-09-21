#!/usr/bin/env python
# coding:utf8


class ChineseGetter(object):
    def __init__(self):
        self.trans = dict(dog="狗", cat="猫")

    def get(self, item):
        return self.trans.get(item, str(item))


class EnglishGetter(object):
    def get(self, item):
        return str(item)


def get_localizer(language="English"):
    languages = dict(English=EnglishGetter, Chinese=ChineseGetter)
    return languages[language]()


if __name__ == "__main__":
    e, c = get_localizer(), get_localizer(language="Chinese")
    for item in "dog parrot cat bear".split():
        print(e.get(item), c.get(item))
