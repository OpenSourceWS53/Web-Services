package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;

<<<<<<<< HEAD:src/main/java/open/source/agriculture/chaquitaclla/chaquitacllaplatformopen/crops/domain/model/commands/CreateCareCommand.java
public record CreateCareCommand(String description, Long cropId) {
========
public record UpdateSowingCommand(Long Id, DateRange dateRange, int areaLand) {
>>>>>>>> refs/heads/bc-sowings:src/main/java/open/source/agriculture/chaquitaclla/chaquitacllaplatformopen/sowings/domain/model/commands/UpdateSowingCommand.java
}