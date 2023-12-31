package com.svalero.airadmin.contract.airplanesContracts;

import com.svalero.airadmin.domain.Airplane;


public interface AirplaneDetailsContract {
    interface Model {
        interface OnLoadOneAirplaneListener {
            void onLoadOneAirplaneSuccess(Airplane airplane);
            void onLoadOneAirplaneError(String message);
        }
        void loadOneAirplane(long airplaneId, OnLoadOneAirplaneListener listener);
    }
    interface Presenter {
        void loadOneAirplane(long airplaneId);
    }

    interface View {
        void listOneAirplane(Airplane airplanes);
        void showMessage(Airplane airplane);

        void showMessage(String message);
    }


}

