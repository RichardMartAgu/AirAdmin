package com.svalero.airadmin.contract.airplanesContracts;

import com.svalero.airadmin.domain.Airplane;


public interface AirplaneRegisterContract {

    interface Model {
        interface OnRegisterAirplaneListener {
            void onRegisterAirplaneSuccess();
            void onRegisterAirplaneError(String message);
        }
        void registerAirplane(OnRegisterAirplaneListener listener, Airplane airplane);
    }
    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void registerAirplane(Airplane airplane);
    }
}