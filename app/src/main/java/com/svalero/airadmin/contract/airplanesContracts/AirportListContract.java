package com.svalero.airadmin.contract.airplanesContracts;

import com.svalero.airadmin.domain.Airplane;

import java.util.List;

public interface AirportListContract {

    interface Model {
        interface OnLoadAllAirplaneListener {
            void onLoadAllAirplaneSuccess(List<Airplane> airplane);

            void onLoadAllAirplaneError(int stringId);
        }

        void loadAllAirplane(OnLoadAllAirplaneListener listener);
    }

    interface View {
        void listAirplane(List<Airplane> airplane);
        void showMessage(int stringId);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllAirplanes();
    }
}

