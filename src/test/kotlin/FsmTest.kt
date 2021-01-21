import mu.KotlinLogging
import org.testng.annotations.Test

class FsmTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun hungryLionSeesAntelope() {
        val lion = Lion(LionState.HUNGRY, LionAction.LOOK)
        logger.info { "state before: Lion is ${lion.state}" }
        logger.info { "What happened: Lion saw ${InputData.ANTELOPE}" }

        assert(LionFsm(lion, InputData.ANTELOPE).lion == Lion(LionState.WELL_FED, LionAction.EAT))
    }

    @Test
    fun hungryLionSeesHunter() {
        val lion = Lion(LionState.HUNGRY, LionAction.LOOK)
        logger.info { "state before: Lion is ${lion.state}" }
        logger.info { "What happened: Lion saw ${InputData.HUNTER}" }

        assert(LionFsm(lion, InputData.HUNTER).lion == lion.apply { action = LionAction.RUN_AWAY })
    }

    @Test
    fun hungryLionSeesTree() {
        val lion = Lion(LionState.HUNGRY, LionAction.LOOK)
        logger.info { "state before: Lion is ${lion.state}" }
        logger.info { "What happened: Lion saw ${InputData.TREE}" }

        assert(LionFsm(lion, InputData.TREE).lion == lion.apply { action = LionAction.SLEEP })
    }


    @Test
    fun fedLionSeesTree() {
        val lion = Lion(LionState.WELL_FED, LionAction.LOOK)
        logger.info { "state before: Lion is ${lion.state}" }
        logger.info { "What happened: Lion saw ${InputData.TREE}" }

        assert(LionFsm(lion, InputData.TREE).lion == lion.apply { state = LionState.HUNGRY; })
    }

    @Test
    fun fedLionSeesHunter() {
        val lion = Lion(LionState.WELL_FED, LionAction.LOOK)
        logger.info { "state before: Lion is ${lion.state}" }
        logger.info { "What happened: Lion saw ${InputData.HUNTER}" }

        assert(LionFsm(lion, InputData.HUNTER).lion == lion.apply {
            state = LionState.HUNGRY; action = LionAction.RUN_AWAY
        })
    }

    @Test
    fun fedLionSeesAntelope() {
        val lion = Lion(LionState.WELL_FED, LionAction.LOOK)

        logger.info { "state before: Lion is ${lion.state}" }
        logger.info { "What happened: Lion saw ${InputData.ANTELOPE}" }

        assert(LionFsm(lion, InputData.ANTELOPE).lion == lion.apply { state = LionState.HUNGRY; })
    }
}