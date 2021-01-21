class LionFsm(var lion: Lion, val situation: InputData) {

    init {
        this.lion = when (situation) {
            InputData.ANTELOPE -> ifAntelope(lion = lion)
            InputData.HUNTER -> ifHunter(lion)
            InputData.TREE -> ifTree(lion)
        }
    }


    private fun ifAntelope(lion: Lion) = when (lion.state) {
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

    private fun ifHunter(lion: Lion) = when (lion.state) {
        LionState.HUNGRY -> lion.also { it.action = LionAction.RUN_AWAY }
        LionState.WELL_FED -> lion.apply {
            action = LionAction.RUN_AWAY;
            state = LionState.HUNGRY
        }
        else -> lion
    }

    private fun ifTree(lion: Lion) = when (lion.state) {
        LionState.HUNGRY -> lion.apply { action = LionAction.SLEEP }
        LionState.WELL_FED -> lion.apply {
            action = LionAction.LOOK;
            state = LionState.HUNGRY
        }
        else -> lion
    }
}