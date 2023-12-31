package com.svalero.airadmin.contract.airplanesContracts;

import com.svalero.airadmin.domain.Airplane;

public interface AirplaneEditContract {

    interface Model {
        void loadEditOneAirplane(long airplaneId, Airplane airplane, AirplaneEditContract.Model.OnLoadEditOneAirplaneListener listener);
        interface OnLoadEditOneAirplaneListener {
            void onLoadEditOneAirplaneSuccess();
            void onLoadEditOneAirplaneError(String message);
        }
    }
    interface Presenter {
        void editOneAirplane(long airplaneId,Airplane airplane);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
        }
    }

