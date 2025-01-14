package com.patloew.rxlocation;

import android.location.Location;
import androidx.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import io.reactivex.MaybeEmitter;

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
class LocationLastMaybeOnSubscribe extends RxLocationMaybeOnSubscribe<Location> {

    LocationLastMaybeOnSubscribe(@NonNull RxLocation rxLocation) {
        super(rxLocation, null, null);
    }

    @Override
    protected void onGoogleApiClientReady(GoogleApiClient apiClient, MaybeEmitter<Location> emitter) {
        //noinspection MissingPermission
        Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);

        if (location != null) {
            emitter.onSuccess(location);
        } else {
            emitter.onComplete();
        }
    }
}
