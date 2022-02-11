# tile.glass.name=Glass
# tile.stained_glass_pane_strong_rainbow.white.name=Rainbow Stained White Stained Glass Pane of Strength
glass = ["glass", "유리"]

type1 = [
    ["", "", "", ""],
    ["", "_pane", "", " 판"],
    ["", "_panel", "", " 패널"]
]

type2 = [
    ["", "", "", ""],
    ["", "_clear", "깨끗한 ", ""],
    ["", "_scratched", "긁힌 ", ""],
    ["", "_crystal", "수정 ", ""],
    ["", "_bright", "조명 ", ""],
    ["", "_dim", "여명의 ", ""],
    ["", "_dark", "칠흑의 ", ""],
    ["", "_ghostly", "영혼 ", ""],
    ["", "_ethereal", "미묘한 ", ""],
    ["", "_foreboding", "불길한 ", ""],
    ["", "_strong", "강화 ", ""]
]

type3 = [
    ["", "", "", ""],
    ["stained_", ".white", "하얀색 색", ""],
    ["stained_", ".orange", "주황색 색 ", ""],
    ["stained_", ".magenta", "자홍색 색", ""],
    ["stained_", ".light_blue", "하늘색 색", ""],
    ["stained_", ".yellow", "노란색 색", ""],
    ["stained_", ".lime", "연두색 색", ""],
    ["stained_", ".pink", "분홍색 색", ""],
    ["stained_", ".gray", "회색 색", ""],
    ["stained_", ".light_gray", "회백색 색", ""],
    ["stained_", ".cyan", "청록색 색", ""],
    ["stained_", ".purple", "보라색 색", ""],
    ["stained_", ".blue", "파란색 색", ""],
    ["stained_", ".brown", "갈색 색", ""],
    ["stained_", ".green", "초록색 색", ""],
    ["stained_", ".red", "빨간색 색", ""],
    ["stained_", ".black", "검은색 색", ""],
    ["", "_rainbow", "무지개색 색", ""]
]

for t1 in type1:
    for t2 in type2:
        for t3 in type3:
            print("tile." + t3[0] + t2[0] + t1[0] + glass[0] + t1[1] + t2[1] + t3[1] + ".name=" + t2[2] + t3[2] + t1[2] + glass[1] + t1[3] + t3[3] + t2[3])