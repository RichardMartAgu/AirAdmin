package com.svalero.airadmin.contract.airplanesContracts;

public interface AirplaneDeleteContract {

    interface Model {
        void loadDeleteOneAirplane(long airplaneId, OnLoadDeleteOneAirplaneListener listener);
        interface OnLoadDeleteOneAirplaneListener {
            void onLoadDeleteOneAirplaneSuccess();
            void onLoadDeleteOneAirplaneError(String message);
        }
    }
    interface Presenter {
        void deleteOneAirplane(long airplaneId);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }
}
