package com.flexpag.paymentscheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration//Nova classe de configuração do spring security
public class SecurityConfig {


}
