import static globals.Globals.*

BR = recipemap('batch_reactor')
LCR = recipemap('large_chemical_reactor')
FBR = recipemap('fixed_bed_reactor')
CSTR = recipemap('continuous_stirred_tank_reactor')
SIFTER = recipemap('sifter')
DISTILLATION_TOWER = recipemap('distillation_tower')
PHASE_SEPARATOR = recipemap('phase_separator')
ASSEMBLER = recipemap('assembler')
CHEMICAL_BATH = recipemap('chemical_bath')
DISTILLERY = recipemap('distillery')
ASSEMBLER = recipemap('assembler')

// Spinneret

ASSEMBLER.recipeBuilder()
    .circuitMeta(11)
    .inputs(ore('plateStainlessSteel') * 4)
    .inputs(ore('springNichrome'))
    .inputs(ore('pipeTinyFluidPlastic'))
    .inputs(ore('circuitHv'))
    .inputs(metaitem('electric.pump.hv'))
    .outputs(metaitem('spinneret'))
    .duration(600)
    .EUt(480)
    .buildAndRegister()

// 4-nitroaniline

BR.recipeBuilder()
    .inputs(ore('dustFourChloronitrobenzene'))
    .fluidInputs(fluid('ammonia') * 2000)
    .fluidOutputs(fluid('four_nitroaniline_solution') * 1000)
    .outputs(metaitem('dustAmmoniumChloride') * 6)
    .duration(600)
    .EUt(120)
    .buildAndRegister()

// p-Phenylenediamine

BCR.recipeBuilder()
    .fluidInputs(fluid('four_nitroaniline_solution') * 50)
    .fluidInputs(fluid('hydrogen') * 300)
    .fluidOutputs(fluid('para_phenylenediamine_solution') * 150)
    .duration(200)
    .EUt(30)
    .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
    .fluidInputs(fluid('para_phenylenediamine_solution') * 3000)
    .fluidOutputs(fluid('toluene') * 1000)
    .fluidOutputs(fluid('steam') * 2000)
    .outputs(metaitem('dustParaPhenylenediamine') * 16)
    .duration(200)
    .EUt(30)
    .buildAndRegister()

// Kevlar

LCR.recipeBuilder()
    .inputs(ore('dustParaPhenylenediamine') * 16)
    .inputs(ore('dustTerephthaloylChloride') * 16)
    .notConsumable(fluid('n_methyl_ii_pyrrolidone') * 4000)
    .fluidInputs(fluid('soda_ash_solution') * 1000)
    .outputs(metaitem('fiberWetKevlar') * 8)
    .fluidOutputs(fluid('carbon_dioxide') * 1000)
    .fluidOutputs(fluid('diluted_calcium_chloride_solution') * 2000)
    .EUt(1920)
    .duration(300)
    .buildAndRegister()

LCR.recipeBuilder()
    .inputs(ore('dustParaPhenylenediamine') * 16)
    .inputs(ore('dustTerephthaloylChloride') * 16)
    .fluidInputs(fluid('dimethylacetamide') * 4000)
    .fluidOutputs(fluid('kevlar_polymerization_mix') * 4000)
    .EUt(1920)
    .duration(300)
    .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
    .fluidInputs(fluid('kevlar_polymerization_mix') * 4000)
    .fluidInputs(fluid('air') * 1000)
    .fluidInputs(fluid('water') * 100)
    .notConsumable(metaitem('spinneret'))
    .outputs(metaitem('fiberWetKevlar') * 8)
    .fluidOutputs(fluid('spent_dimethylacetamide') * 4000)
    .fluidOutputs(fluid('wastewater') * 100)
    .duration(300)
    .EUt(480)
    .buildAndRegister()

DISTILLERY.recipeBuilder()
    .fluidInputs(fluid('spent_dimethylacetamide') * 4000)
    .outputs(metaitem('dustCalciumChloride') * 3)
    .fluidOutputs(fluid('dimethylacetamide') * 4000)
    .duration(20)
    .EUt(30)
    .buildAndRegister()

ASSEMBLER.recipeBuilder()
    .inputs(ore('fiberKevlar') * 8)
    .outputs(metaitem('plateKevlar'))
    .EUt(1920)
    .duration(300)
    .buildAndRegister()

// m-Phenylenediamine

CSTR.recipeBuilder()
    .fluidInputs(fluid('nitrobenzene') * 50)
    .fluidInputs(fluid('nitration_mixture') * 100)
    .fluidOutputs(fluid('acidic_dinitrobenzene_mixture') * 100)
    .duration(10)
    .EUt(480)
    .buildAndRegister()

PHASE_SEPARATOR.recipeBuilder()
    .fluidInputs(fluid('acidic_dinitrobenzene_mixture') * 2000)
    .outputs(metaitem('dustDinitrobenzeneMixture'))
    .fluidOutputs(fluid('diluted_sulfuric_acid') * 2000)
    .duration(50)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustDinitrobenzeneMixture'))
    .fluidInputs(fluid('sodium_hydroxide_solution') * 100)
    .chancedOutput(metaitem('dustOneThreeDinitrobenzene'), 8800, 0)
    .fluidOutputs(fluid('wastewater') * 100)
    .duration(200)
    .EUt(480)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustOneThreeDinitrobenzene'))
    .fluidInputs(fluid('hydrogen') * 4000)
    .notConsumable(ore('dustActivatedRaneyNickel') * 1)
    .outputs(metaitem('dustMetaPhenylenediamine') * 16)
    .duration(20)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

// Nomex

LCR.recipeBuilder()
    .inputs(ore('dustMetaPhenylenediamine') * 16)
    .inputs(ore('dustIsophthaloylChloride') * 16)
    .fluidInputs(fluid('dimethylacetamide') * 4000)
    .fluidOutputs(fluid('nomex_polymerization_mix') * 4000)
    .EUt(1920)
    .duration(300)
    .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
    .fluidInputs(fluid('nomex_polymerization_mix') * 4000)
    .fluidInputs(fluid('air') * 1000)
    .fluidInputs(fluid('water') * 100)
    .notConsumable(metaitem('spinneret'))
    .outputs(metaitem('fiberWetNomex') * 8)
    .fluidOutputs(fluid('spent_dimethylacetamide') * 4000)
    .fluidOutputs(fluid('wastewater') * 100)
    .duration(300)
    .EUt(480)
    .buildAndRegister()