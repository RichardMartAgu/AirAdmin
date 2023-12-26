package com.svalero.airadmin.contract;

import com.svalero.airadmin.domain.Airport;

public interface AirportRegisterContract {

    interface Model {
        interface OnRegisterTasksListener {
            void onRegisterTaskSuccess();
            void onRegisterTasksError(String message);
        }
        void registerTask(OnRegisterTasksListener listener, Airport airport);
    }
}