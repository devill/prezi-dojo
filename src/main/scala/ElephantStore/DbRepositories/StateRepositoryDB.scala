package ElephantStore.DbRepositories

import ElephantStore.Core.{StateDto, StateRepository}
import ElephantStore.StateRepository

class StateRepositoryDB extends StateRepository {

    // TODO Implement actual DB request
    def get(stateCode:String): Option[StateDto] = ???
}
