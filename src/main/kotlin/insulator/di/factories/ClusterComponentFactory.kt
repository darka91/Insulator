package insulator.di.factories

import insulator.di.components.ClusterComponent
import insulator.di.components.DaggerClusterComponent
import insulator.di.components.InsulatorComponent
import insulator.lib.configuration.model.Cluster
import javax.inject.Inject

class ClusterComponentFactory @Inject constructor(insulatorComponent: InsulatorComponent) :
    CachedFactory<Cluster, ClusterComponent>({ cluster ->
        DaggerClusterComponent.factory().build(insulatorComponent, cluster)
    })