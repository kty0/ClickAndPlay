package fr.epita.presentation.registration.dto;

public class TableRegistrationDto {
    private String playerId;
    private String tableId;
    private boolean registered;

    public TableRegistrationDto(String playerId, String tableId, boolean registered) {
        this.playerId = playerId;
        this.tableId = tableId;
        this.registered = registered;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTableId() {
        return tableId;
    }

    public boolean isRegistered() {
        return registered;
    }
}
