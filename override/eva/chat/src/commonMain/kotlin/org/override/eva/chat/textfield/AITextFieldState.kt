package org.override.eva.chat.textfield

/**
 * Estado del TextField de IA
 */
enum class AITextFieldState {
    IDLE,       // Estado normal
    TYPING,     // Usuario escribiendo
    SENDING,    // Enviando mensaje
    RECEIVING,  // Recibiendo respuesta de IA
    ERROR       // Error en la comunicación
}
