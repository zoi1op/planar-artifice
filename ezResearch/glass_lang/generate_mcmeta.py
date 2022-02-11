import sys

print(sys.argv)
if (len(sys.argv) < 2):
    prefix = ""
else:
    prefix = sys.argv[1]

colors = ["white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"]
with open("./glass" + prefix + ".png.mcmeta", "w") as f:
    f.write('''
    {
        "ctm":{
            "ctm_version": 1,
            "type":"CTM",
            "layer":"CUTOUT",
            "textures":[
                "planarartifice:blocks/glass/glass''' + prefix + '''_ctm"
            ]
        }
    }
    ''')
with open("./glass" + prefix + "_rainbow.png.mcmeta", "w") as f:
    f.write('''
    {
        "animation": {
            "interpolate": true
        }, "ctm": {
            "ctm_version": 1,
            "type":"sctm",
            "layer":"CUTOUT",
            "textures":[
                "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow_ctm"
            ]
        }
    }
    ''')
for color in colors:
    with open("./glass" + prefix + "_" + color + ".png.mcmeta", "w") as f:
        f.write('''
        {
            "ctm": {
                "ctm_version": 1,
                "type":"CTM",
                "layer":"TRANSLUCENT",
                "textures":[
                    "planarartifice:blocks/glass/glass''' + prefix + '_' + color + '''_ctm"
                ]
            }
        }
        ''')