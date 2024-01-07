package com.svalero.airadmin.contract.airportsContracts;

public interface AirportDeleteContract {

    interface Model {
        void loadDeleteOneAirport(long airportId, OnLoadDeleteOneAirportListener listener);
        interface OnLoadDeleteOneAirportListener {
            void onLoadDeleteOneAirportSuccess();
            void onLoadDeleteOneAirportError(String message);
        }
    }
    interface Presenter {
        void deleteOneAirport(long airplaneId);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }
}
