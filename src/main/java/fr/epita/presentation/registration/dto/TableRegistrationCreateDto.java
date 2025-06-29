package fr.epita.presentation.registration.dto;

public class TableRegistrationCreateDto {
    private String playerId;
    private String tableId;

    public TableRegistrationCreateDto(String playerId, String tableId) {
        this.playerId = playerId;
        this.tableId = tableId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
