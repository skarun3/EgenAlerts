package com.egen.alerts.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.egen.alerts.main.entity.Alert;
import com.egen.alerts.main.entity.Status;
import com.egen.alerts.main.service.AlertService;

@RestController
@RequestMapping(path = "/alert")
public class AlertsController {

	@Autowired
	private AlertService objAlertService;

	@RequestMapping(path = "", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody Alert objAlert) {
		try {
			objAlertService.create(objAlert);
			return new ResponseEntity<Status>(new Status(201, "User added Successfully !"), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Status>(new Status(0, e.toString()), HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Alert> readAlert() {
		List<Alert> alertsList = objAlertService.getAlerts();
		return alertsList;

	}

	@RequestMapping(path = "/query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Alert> readAlertsByTime(@RequestParam(required = false, value = "from") long from,
			@RequestParam(required = false, value = "to") long to) {
		if (from == 0 || to == 0 || from > to) {
			return new ArrayList<Alert>();
		}
		List<Alert> alertsList = objAlertService.getAlertsbyTime(from, to);
		return alertsList;
	}
}
