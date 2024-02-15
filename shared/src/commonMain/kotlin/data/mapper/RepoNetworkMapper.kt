package data.mapper

import core.network.model.RepoNetModel
import domain.model.Repo

class RepoNetworkMapper {

    fun mapRepoFromNet(netModel: RepoNetModel): Repo {
        return Repo(
            id = netModel.id,
            name = netModel.name,
            description = netModel.description
        )
    }
}