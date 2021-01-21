fun main() {
    val arr = arrayOf(InputData.ANTELOPE, InputData.HUNTER, InputData.TREE)
    val arr2 = arrayOf(Lion(LionState.HUNGRY, LionAction.LOOK), Lion(LionState.WELL_FED, LionAction.LOOK))

    arr.forEachIndexed { _, inputData ->
        arr2.forEachIndexed { _, lion -> println(
            "Lion was ${lion.state} when lion saw $inputData  \n ${fsm(lion, inputData)}")}
    }
}

fun fsm(lion: Lion, situation: InputData) = when (situation) {
    InputData.ANTELOPE -> ifAntelope(lion = lion)
    InputData.HUNTER -> ifHunter(lion)
    InputData.TREE -> ifTree(lion)
}


fun ifAntelope(lion: Lion) = when (lion.state) {
    LionState.HUNGRY -> lion.apply {
        action = LionAction.EAT
        state = LionState.WELL_FED
    }
    LionState.WELL_FED -> lion.apply {
        action = LionAction.SLEEP
        state = LionState.HUNGRY
    }
    else -> lion
}

fun ifHunter(lion: Lion) = when (lion.state) {
    LionState.HUNGRY -> lion.also { it.action = LionAction.RUN_AWAY }
    LionState.WELL_FED -> lion.apply {
        action = LionAction.RUN_AWAY;
        state = LionState.HUNGRY
    }
    else -> lion
}

fun ifTree(lion: Lion) = when (lion.state) {
    LionState.HUNGRY -> lion.apply { action = LionAction.SLEEP }
    LionState.WELL_FED -> lion.apply {
        action = LionAction.LOOK;
        state = LionState.HUNGRY
    }
    else -> lion
}
