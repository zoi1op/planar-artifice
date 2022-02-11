variant = ["_clear", "_scratched", "_crystal", "_dim", "_dark", "_bright", "_ghostly", "_ethereal", "_foreboding", "_strong"]
colors = ["white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"]
for prefix in variant:
    with open("./glass" + prefix + ".json", "w") as f:
        f.write('''
        {
            "forge_marker": 1,
            "defaults": {
                "model": "planarartifice:cube_ctm_cutout",
                "textures": {
                    "all": "planarartifice:blocks/glass/glass''' + prefix + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''",
                    "connected_tex": "planarartifice:blocks/glass/glass''' + prefix + '''_ctm"
                }
            },
            "variants": {
                "normal": [{}],
                "inventory": [{}]
            }
        }
        ''')
    with open("./glass" + prefix + "_rainbow.json", "w") as f:
        f.write('''
        {
            "forge_marker": 1,
            "defaults": {
                "model": "planarartifice:cube_ctm_translucent",
                "textures": {
                    "all": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow",
                    "connected_tex": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow_ctm"
                }
            },
            "variants": {
                "normal": [{}],
                "inventory": [{}]
            }
        }
        ''')
    with open("./glass_panel" + prefix + ".json", "w") as f:
        f.write('''
        {
            "forge_marker": 1,
            "defaults": {
                "model": "planarartifice:panel_ctm_cutout",
                "textures": {
                    "all": "planarartifice:blocks/glass/glass''' + prefix + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''",
                    "connected_tex": "planarartifice:blocks/glass/glass''' + prefix + '''_ctm"
                }
            },
            "variants": {
                "normal": [{}],
                "inventory": [{}]
            }
        }
        ''')
    with open("./glass_panel" + prefix + "_rainbow.json", "w") as f:
        f.write('''
        {
            "forge_marker": 1,
            "defaults": {
                "model": "planarartifice:panel_ctm_translucent",
                "textures": {
                    "all": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow",
                    "connected_tex": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow_ctm"
                }
            },
            "variants": {
                "normal": [{}],
                "inventory": [{}]
            }
        }
        ''')

    ##################### STAINED GLASS #########
    with open("./stained_glass" + prefix + ".json", "w") as f:
        q = ''
        for color in colors:
            q += '''"color=''' + color + '''": [{
                    "textures": {
                        "all": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                        "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                        "connected_tex": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                    }
                }],'''
        f.write('''
        {
            "forge_marker": 1,
            "defaults": {
                "model": "planarartifice:cube_ctm_translucent"
            },
            "variants": {
                ''' + q[:-1] + '''
            }
        }
        ''')
    with open("./stained_glass_panel" + prefix + ".json", "w") as f:
        q = ''
        for color in colors:
            q += '''"color=''' + color + '''": [{
                    "textures": {
                        "all": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                        "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                        "connected_tex": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                    }
                }],'''
        f.write('''
        {
            "forge_marker": 1,
            "defaults": {
                "model": "planarartifice:panel_ctm_translucent"
            },
            "variants": {
                ''' + q[:-1] + '''
            }
        }
        ''')

    ################# GLASS PANE ###########
    with open("./glass_pane" + prefix + ".json", "w") as f:
        f.write('''       
        {
            "forge_marker": 1,
            "defaults": {
                "transform": "forge:default-item",
                "textures": {
                    "edge"   : "blocks/glass_pane_top",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_ctm"
                }
            },
            "variants": {
                "inventory": [{ "model": "planarartifice:pane/ctm_ew" }],
                "east=false,north=false,south=false,west=false": [{ "model": "planarartifice:pane/post" }],

                "east=false,north=true,south=false,west=false" : [{ "model": "planarartifice:pane/ctm_n" }],
                "east=true,north=false,south=false,west=false" : [{ "model": "planarartifice:pane/ctm_e" }],
                "east=false,north=false,south=true,west=false" : [{ "model": "planarartifice:pane/ctm_s" }],
                "east=false,north=false,south=false,west=true" : [{ "model": "planarartifice:pane/ctm_w" }],

                "east=true,north=true,south=false,west=false"  : [{ "model": "planarartifice:pane/ctm_ne" }],
                "east=true,north=false,south=true,west=false"  : [{ "model": "planarartifice:pane/ctm_se" }],
                "east=false,north=false,south=true,west=true"  : [{ "model": "planarartifice:pane/ctm_sw" }],
                "east=false,north=true,south=false,west=true"  : [{ "model": "planarartifice:pane/ctm_nw" }],

                "east=false,north=true,south=true,west=false"  : [{ "model": "planarartifice:pane/ctm_ns" }],
                "east=true,north=false,south=false,west=true"  : [{ "model": "planarartifice:pane/ctm_ew" }],

                "east=true,north=true,south=true,west=false"   : [{ "model": "planarartifice:pane/ctm_nse" }],
                "east=true,north=false,south=true,west=true"   : [{ "model": "planarartifice:pane/ctm_sew" }],
                "east=false,north=true,south=true,west=true"   : [{ "model": "planarartifice:pane/ctm_nsw" }],
                "east=true,north=true,south=false,west=true"   : [{ "model": "planarartifice:pane/ctm_new" }],

                "east=true,north=true,south=true,west=true"    : [{ "model": "planarartifice:pane/ctm_nsew" }]
            }
        }
        ''')
    with open("./glass_pane" + prefix + "_rainbow.json", "w") as f:
        f.write('''       
        {
            "forge_marker": 1,
            "defaults": {
                "transform": "forge:default-item",
                "textures": {
                    "edge"   : "planarartifice:blocks/glass/glass_pane_top_rainbow",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_rainbow_ctm"
                }
            },
            "variants": {
                "inventory": [{ "model": "planarartifice:pane/ctm_ew_translucent" }],
                "east=false,north=false,south=false,west=false": [{ "model": "planarartifice:pane/post_translucent" }],

                "east=false,north=true,south=false,west=false" : [{ "model": "planarartifice:pane/ctm_n_translucent" }],
                "east=true,north=false,south=false,west=false" : [{ "model": "planarartifice:pane/ctm_e_translucent" }],
                "east=false,north=false,south=true,west=false" : [{ "model": "planarartifice:pane/ctm_s_translucent" }],
                "east=false,north=false,south=false,west=true" : [{ "model": "planarartifice:pane/ctm_w_translucent" }],

                "east=true,north=true,south=false,west=false"  : [{ "model": "planarartifice:pane/ctm_ne_translucent" }],
                "east=true,north=false,south=true,west=false"  : [{ "model": "planarartifice:pane/ctm_se_translucent" }],
                "east=false,north=false,south=true,west=true"  : [{ "model": "planarartifice:pane/ctm_sw_translucent" }],
                "east=false,north=true,south=false,west=true"  : [{ "model": "planarartifice:pane/ctm_nw_translucent" }],

                "east=false,north=true,south=true,west=false"  : [{ "model": "planarartifice:pane/ctm_ns_translucent" }],
                "east=true,north=false,south=false,west=true"  : [{ "model": "planarartifice:pane/ctm_ew_translucent" }],

                "east=true,north=true,south=true,west=false"   : [{ "model": "planarartifice:pane/ctm_nse_translucent" }],
                "east=true,north=false,south=true,west=true"   : [{ "model": "planarartifice:pane/ctm_sew_translucent" }],
                "east=false,north=true,south=true,west=true"   : [{ "model": "planarartifice:pane/ctm_nsw_translucent" }],
                "east=true,north=true,south=false,west=true"   : [{ "model": "planarartifice:pane/ctm_new_translucent" }],

                "east=true,north=true,south=true,west=true"    : [{ "model": "planarartifice:pane/ctm_nsew_translucent" }]
            }
        }
        ''')

    ############ STAINED GLASS PANE #####
    with open("./stained_glass_pane" + prefix + ".json", "w") as f:
        q = ''
        for color in colors:
            q += '''"color=''' + color + ''',east=false,north=false,south=false,west=false": [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/post_translucent" }],
                "color=''' + color + ''',east=false,north=true,south=false,west=false" : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_n_translucent" }],
                "color=''' + color + ''',east=true,north=false,south=false,west=false" : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_e_translucent" }],
                "color=''' + color + ''',east=false,north=false,south=true,west=false" : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_s_translucent" }],
                "color=''' + color + ''',east=false,north=false,south=false,west=true" : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_w_translucent" }],
                "color=''' + color + ''',east=true,north=true,south=false,west=false"  : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_ne_translucent" }],
                "color=''' + color + ''',east=true,north=false,south=true,west=false"  : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_se_translucent" }],
                "color=''' + color + ''',east=false,north=false,south=true,west=true"  : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_sw_translucent" }],
                "color=''' + color + ''',east=false,north=true,south=false,west=true"  : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_nw_translucent" }],'''
            q += '''"color=''' + color + ''',east=false,north=true,south=true,west=false"  : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_ns_translucent" }],
                "color=''' + color + ''',east=true,north=false,south=false,west=true"  : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_ew_translucent" }],
                "color=''' + color + ''',east=true,north=true,south=true,west=false"   : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_nse_translucent" }],
                "color=''' + color + ''',east=true,north=false,south=true,west=true"   : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_sew_translucent" }],
                "color=''' + color + ''',east=false,north=true,south=true,west=true"   : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_nsw_translucent" }],
                "color=''' + color + ''',east=true,north=true,south=false,west=true"   : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_new_translucent" }],
                "color=''' + color + ''',east=true,north=true,south=true,west=true"    : [{ "textures": {
                    "edge"   : "blocks/glass_pane_top_''' + color + '''",
                    "pane"   : "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "particle": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''",
                    "pane_ct": "planarartifice:blocks/glass/glass''' + prefix + '''_''' + color + '''_ctm"
                }, "model": "planarartifice:pane/ctm_nsew_translucent" }],'''
        f.write('''       
        {
            "forge_marker": 1,
            "defaults": {
                "transform": "forge:default-item"
            },
            "variants": {
                ''' + q[:-1] + '''
            }
        }
        ''')
with open("./glass_rainbow.json", "w") as f:
    f.write('''
    {
        "forge_marker": 1,
        "defaults": {
            "model": "planarartifice:cube_ctm_translucent",
            "textures": {
                "all": "planarartifice:blocks/glass/glass_rainbow",
                "particle": "planarartifice:blocks/glass/glass_rainbow",
                "connected_tex": "planarartifice:blocks/glass/glass_rainbow_ctm"
            }
        },
        "variants": {
            "normal": [{}],
            "inventory": [{}]
        }
    }
    ''')
with open("./glass_pane_rainbow.json", "w") as f:
    f.write('''       
    {
        "forge_marker": 1,
        "defaults": {
            "transform": "forge:default-item",
            "textures": {
                "edge"   : "planarartifice:blocks/glass/glass_pane_top_rainbow",
                "pane"   : "planarartifice:blocks/glass/glass_rainbow",
                "particle": "planarartifice:blocks/glass/glass_rainbow",
                "pane_ct": "planarartifice:blocks/glass/glass_rainbow_ctm"
            }
        },
        "variants": {
            "inventory": [{ "model": "planarartifice:pane/ctm_ew_translucent" }],
            "east=false,north=false,south=false,west=false": [{ "model": "planarartifice:pane/post_translucent" }],

            "east=false,north=true,south=false,west=false" : [{ "model": "planarartifice:pane/ctm_n_translucent" }],
            "east=true,north=false,south=false,west=false" : [{ "model": "planarartifice:pane/ctm_e_translucent" }],
            "east=false,north=false,south=true,west=false" : [{ "model": "planarartifice:pane/ctm_s_translucent" }],
            "east=false,north=false,south=false,west=true" : [{ "model": "planarartifice:pane/ctm_w_translucent" }],

            "east=true,north=true,south=false,west=false"  : [{ "model": "planarartifice:pane/ctm_ne_translucent" }],
            "east=true,north=false,south=true,west=false"  : [{ "model": "planarartifice:pane/ctm_se_translucent" }],
            "east=false,north=false,south=true,west=true"  : [{ "model": "planarartifice:pane/ctm_sw_translucent" }],
            "east=false,north=true,south=false,west=true"  : [{ "model": "planarartifice:pane/ctm_nw_translucent" }],

            "east=false,north=true,south=true,west=false"  : [{ "model": "planarartifice:pane/ctm_ns_translucent" }],
            "east=true,north=false,south=false,west=true"  : [{ "model": "planarartifice:pane/ctm_ew_translucent" }],

            "east=true,north=true,south=true,west=false"   : [{ "model": "planarartifice:pane/ctm_nse_translucent" }],
            "east=true,north=false,south=true,west=true"   : [{ "model": "planarartifice:pane/ctm_sew_translucent" }],
            "east=false,north=true,south=true,west=true"   : [{ "model": "planarartifice:pane/ctm_nsw_translucent" }],
            "east=true,north=true,south=false,west=true"   : [{ "model": "planarartifice:pane/ctm_new_translucent" }],

            "east=true,north=true,south=true,west=true"    : [{ "model": "planarartifice:pane/ctm_nsew_translucent" }]
        }
    }
    ''')
with open("./glass_panel.json", "w") as f:
    f.write('''
    {
        "forge_marker": 1,
        "defaults": {
            "model": "planarartifice:panel_ctm_cutout",
            "textures": {
                "all": "blocks/glass",
                "particle": "blocks/glass",
                "connected_tex": "blocks/glass_ctm"
            }
        },
        "variants": {
            "normal": [{}],
            "inventory": [{}]
        }
    }
    ''')
with open("./glass_panel_rainbow.json", "w") as f:
    f.write('''
    {
        "forge_marker": 1,
        "defaults": {
            "model": "planarartifice:panel_ctm_translucent",
            "textures": {
                "all": "planarartifice:blocks/glass/glass_rainbow",
                "particle": "planarartifice:blocks/glass/glass_rainbow",
                "connected_tex": "planarartifice:blocks/glass/glass_rainbow_ctm"
            }
        },
        "variants": {
            "normal": [{}],
            "inventory": [{}]
        }
    }
    ''')
with open("./stained_glass_panel.json", "w") as f:
    q = ''
    for color in colors:   
        q += '''"color=''' + color + '''": [{
                "textures": {
                    "all": "blocks/glass_''' + color + '''",
                    "particle": "blocks/glass_''' + color + '''",
                    "connected_tex": "blocks/glass_''' + color + '''_ctm"
                }
            }],'''
    f.write('''
    {
        "forge_marker": 1,
        "defaults": {
            "model": "planarartifice:panel_ctm_translucent"
        },
        "variants": {
            ''' + q[:-1] + '''
        }
    }
    ''')