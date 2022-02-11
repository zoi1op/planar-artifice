# tile.glass.name=Glass
# tile.stained_glass_pane_strong_rainbow.white.name=Rainbow Stained White Stained Glass Pane of Strength
glass = ["glass", "Glass"]

type1 = [
    ["", "", "", ""],
    ["", "_pane", "", " Pane"],
    ["", "_panel", "", " Panel"]
]

type2 = [
    ["", "", "", ""],
    ["", "_clear", "Clear ", ""],
    ["", "_scratched", "Scratched ", ""],
    ["", "_crystal", "Crystal ", ""],
    ["", "_bright", "", " of Illumination"],
    ["", "_dim", "", " of Dawn"],
    ["", "_dark", "", " of Darkness"],
    ["", "_ghostly", "Ghostly ", ""],
    ["", "_ethereal", "Ethereal ", ""],
    ["", "_foreboding", "Foreboding ", ""],
    ["", "_strong", "Strong ", ""]
]

type3 = [
    ["", "", "", ""],
    ["stained_", ".white", "White Stained ", ""],
    ["stained_", ".orange", "Orange Stained ", ""],
    ["stained_", ".magenta", "Magenta Stained ", ""],
    ["stained_", ".light_blue", "Light Blue Stained ", ""],
    ["stained_", ".yellow", "Yellow Stained ", ""],
    ["stained_", ".lime", "Lime Stained ", ""],
    ["stained_", ".pink", "Pink Stained ", ""],
    ["stained_", ".gray", "Gray Stained ", ""],
    ["stained_", ".light_gray", "Light Gray Stained ", ""],
    ["stained_", ".cyan", "Cyan Stained ", ""],
    ["stained_", ".purple", "Purple Stained ", ""],
    ["stained_", ".blue", "Blue Stained ", ""],
    ["stained_", ".brown", "Brown Stained ", ""],
    ["stained_", ".green", "Green Stained ", ""],
    ["stained_", ".red", "Red Stained ", ""],
    ["stained_", ".black", "Black Stained ", ""],
    ["", "_rainbow", "Tinctura Infused ", ""]
]

for t1 in type1:
    for t2 in type2:
        for t3 in type3:
            print("tile." + t3[0] + t2[0] + t1[0] + glass[0] + t1[1] + t2[1] + t3[1] + ".name=" + t3[2] + t2[2] + t1[2] + glass[1] + t1[3] + t2[3] + t3[3])