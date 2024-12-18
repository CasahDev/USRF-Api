package fr.sacha_casahdev.usrf_api.models;

public enum MatchEvent {
    CREATE_CAN_PLAY,
    UPDATE_CAN_PLAY,
    DELETE_CAN_PLAY,
    ADD_GOAL,
    REMOVE_GOAL,
    ADD_ASSIST,
    REMOVE_ASSIST,
    ADD_YELLOW_CARD,
    REMOVE_YELLOW_CARD,
    ADD_RED_CARD,
    REMOVE_RED_CARD,
    ADD_BLOCKED_SHOT,
    REMOVE_BLOCKED_SHOT,
    ADD_ON_TARGET_SHOT,
    REMOVE_ON_TARGET_SHOT,
    ADD_OFF_TARGET_SHOT,
    REMOVE_OFF_TARGET_SHOT,
    SWITCH_PLAYER,
    ADD_PENALTY,
    REMOVE_PENALTY,
    ADD_INJURY,
    REMOVE_INJURY,
}
