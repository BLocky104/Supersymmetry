import static globals.Globals.*

//Ore dict changes and unification

def unify (ore, p) {
    def pos = p
    def foundMod = false

    for (def item : ore) {
        foundMod = ( item.getItem().getRegistryName().getNamespace() == Globals.mod_priority[pos] && item.getItem().getRegistryName().getNamespace() != 'xtones' )
    }

    if (foundMod) {
        for (def item : ore) {
            if (item.getItem().getRegistryName().getNamespace() != Globals.mod_priority[pos]) {
                ore.remove(item)
                //mods.jei.hide(item)
            }
        }
    } else if (pos < Globals.mod_priority.size() - 1) {
        pos++
        unify(ore, pos)
    }
}

def unify_oredicts(ore_list) {
    for ( def ore : ore_list) {
        if (ore) {
            if (ore.size() > 1) {
                unify(ore, 0)
            }
        }
    }
}

def dicts_ingots = [
    ore('ingotCopper')
]

unify_oredicts(dicts_ingots)