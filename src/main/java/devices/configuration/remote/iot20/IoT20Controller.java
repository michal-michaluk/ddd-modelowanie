package devices.configuration.remote.iot20;

import devices.configuration.remote.Protocols;
import devices.configuration.remote.intervals.IntervalRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
class IoT20Controller {

    public static final Protocols PROTOCOL = Protocols.IoT20;
    private final Clock clock;
    private final IntervalRulesService intervals;

    @PostMapping(path = "/protocols/iot20/bootnotification/{deviceId}",
            consumes = "application/json", produces = "application/json")
    BootNotificationResponse handleBootNotification(@PathVariable String deviceId,
                                                    @RequestBody BootNotificationRequest request) {
        return new BootNotificationResponse(
                Instant.now(clock).toString(),
                intervals.calculateIntervalAsSeconds(request.toDevice(deviceId, PROTOCOL)),
                BootNotificationResponse.Status.Accepted);
    }
}
