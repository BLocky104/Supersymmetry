BR = recipemap('batch_reactor')
CHEMICAL_BATH = recipemap('chemical_bath')
EBF = recipemap('electric_blast_furnace')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')
VACUUM_DT = recipemap('vacuum_distillation')
ZONE_REFINER = recipemap('zone_refiner')
DISTILLERY = recipemap('distillery')

EBF.recipeBuilder()
    .inputs(ore('dustTelluriumResidue')) // 0.25 Na2TeO4, 2 Ag/Au
    .outputs(metaitem('dustRawElectrum') * 2)
    .chancedOutput(metaitem('dustTelluriumSlag'), 2500, 0)
    .blastFurnaceTemp(1400)
    .duration(120)
    .EUt(240)
    .buildAndRegister()

DISTILLERY.recipeBuilder()
    .fluidInputs(fluid('sodium_sulfite_solution') * 1000)
    .outputs(metaitem('dustSodiumSulfite') * 6)
    .fluidOutputs(fluid('water') * 1000)
    .duration(20)
    .EUt(30)
    .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
    .inputs(ore('dustTelluriumSlag'))
    .fluidInputs(fluid('diluted_sulfuric_acid') * 2000)
    .fluidOutputs(fluid('tellurium_liquor') * 1000)
    .duration(120)
    .EUt(30)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustSodiumSulfite') * 6)
    .fluidInputs(fluid('tellurium_liquor') * 1000) // 1 H2TeO4, 1 Na2SO4, 1 H2O
    .outputs(metaitem('dustTelluriumDioxide') * 3)
    .fluidOutputs(fluid('sodium_sulfate_solution') * 2000)
    .duration(120)
    .EUt(30)
    .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
    .inputs(ore('dustTelluriumDioxide') * 3)
    .fluidInputs(fluid('sodium_hydroxide_solution') * 2000)
    .fluidOutputs(fluid('sodium_tellurite_solution') * 3000)
    .duration(120)
    .EUt(30)
    .buildAndRegister()

// 4OH− −→ 2H2O + O2 + 4e−
// TeO3 2− + 3H2O + 4e− −→ Te + 6OH−
// TeO3 2− + H2O −→ O2 + 2OH-

ELECTROLYTIC_CELL.recipeBuilder()
    .notConsumable(metaitem('graphite_electrode'))
    .notConsumable(metaitem('stickStainlessSteel'))
    .fluidInputs(fluid('sodium_tellurite_solution') * 3000)
    .chancedOutput(metaitem('dustTellurium'), 5000, 0)
    .fluidOutputs(fluid('sodium_hydroxide_solution') * 2000)
    .fluidOutputs(fluid('oxygen') * 2000)
    .duration(600)
    .EUt(120)
    .buildAndRegister()

ELECTROLYTIC_CELL.recipeBuilder()
    .notConsumable(metaitem('graphite_electrode'))
    .notConsumable(metaitem('plateTellurium'))
    .fluidInputs(fluid('sodium_tellurite_solution') * 3000)
    .outputs(metaitem('dustTellurium'))
    .fluidOutputs(fluid('sodium_hydroxide_solution') * 2000)
    .fluidOutputs(fluid('oxygen') * 2000)
    .duration(120)
    .EUt(120)
    .buildAndRegister()

// Further refining

VACUUM_DT.recipeBuilder()
    .inputs(ore('dustTellurium'))
    .chancedOutput(metaitem('dustHighPurityTellurium'), 9900, 0)
    .duration(1000)
    .EUt(120)
    .buildAndRegister()

ZONE_REFINER.recipeBuilder()
    .inputs(ore('ingotTellurium'))
    .chancedOutput(metaitem('ingotHighPurityTellurium'), 9900, 0)
    .duration(120)
    .EUt(30)
    .buildAndRegister()