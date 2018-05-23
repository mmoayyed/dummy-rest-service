package hello;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpStatus;
import org.apereo.cas.services.DefaultRegisteredServiceMultifactorPolicy;
import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.RegisteredService;
import org.apereo.cas.services.ReturnAllAttributeReleasePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServicesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

    @DeleteMapping
    public Integer findByServiceId(@RequestBody final RegisteredService service) {
        LOGGER.info("Deleting service [{}]", service.getServiceId());
        return HttpStatus.SC_OK;
    }

    @PostMapping
    public RegisteredService save(@RequestBody final RegisteredService service) {
        LOGGER.info("Saving service [{}]", service.getServiceId());
        return service;
    }

    @GetMapping("/{id}")
    public RegisteredService findServiceById(@PathVariable(name = "id") final String id) {
        if (NumberUtils.isParsable(id)) {
            LOGGER.info("Locating service by id [{}]", id);
            return getRegexRegisteredService();
        } else {
            LOGGER.info("Locating service by service id [{}]", id);
            return getRegexRegisteredService();
        }
    }

    @GetMapping
    public RegisteredService[] load() {
        LOGGER.info("Loading Services...");
        final RegexRegisteredService service = getRegexRegisteredService();
        return new RegisteredService[]{service};
    }

    private static RegexRegisteredService getRegexRegisteredService() {
        final RegexRegisteredService service = new RegexRegisteredService();
        service.setId(1);
        service.setServiceId("https://www.google.com");
        service.setName("Google");
        service.setDescription("Google Application");
        service.setAttributeReleasePolicy(new ReturnAllAttributeReleasePolicy());
        service.setEvaluationOrder(100);
        final DefaultRegisteredServiceMultifactorPolicy mfa = new DefaultRegisteredServiceMultifactorPolicy();
        mfa.setMultifactorAuthenticationProviders(StringUtils.commaDelimitedListToSet("mfa-duo"));
        service.setMultifactorPolicy(mfa);
        return service;
    }
}
