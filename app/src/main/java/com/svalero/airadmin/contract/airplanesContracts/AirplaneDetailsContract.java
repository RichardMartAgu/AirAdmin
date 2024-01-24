package com.svalero.airadmin.contract.airplanesContracts;

import com.svalero.airadmin.domain.Airplane;


public interface AirplaneDetailsContract {
    interface Model {
        interface OnLoadOneAirplaneListener {
            void onLoadOneAirplaneSuccess(Airplane airplane);
            void onLoadOneAirplaneError(int stringId);
        }
        void loadOneAirplane(long airplaneId, OnLoadOneAirplaneListener listener);
    }
    interface Presenter {
        void loadOneAirplane(long airplaneId);
    }

    interface View {
        void listOneAirplane(Airplane airplanes);

        void showMessage(String message);
        void showMessage(int stringId);
    }


}

