package ElephantStore.Core

trait StateRepository {
  def get(stateCode:String): Option[StateDto]
}
