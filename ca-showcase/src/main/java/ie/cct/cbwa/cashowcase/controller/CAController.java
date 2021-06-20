package ie.cct.cbwa.cashowcase.controller;

import ie.cct.cbwa.cashowcase.exceptions.ObjectNotFoundException;
import ie.cct.cbwa.cashowcase.dto.ExpenseDto;
import ie.cct.cbwa.cashowcase.dto.TripDto;
import ie.cct.cbwa.cashowcase.dto.TripSummaryDto;
import ie.cct.cbwa.cashowcase.model.Expense;
import ie.cct.cbwa.cashowcase.model.Trip;
import ie.cct.cbwa.cashowcase.model.User;
import ie.cct.cbwa.cashowcase.util.Database;
import ie.cct.cbwa.cashowcase.util.JWTIssuer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CAController {
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        final List<User> users = Database.users;
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                System.out.println(u);
                return ResponseEntity.ok(JWTIssuer.createJWT(username, "ca-showcase", username, 86400000));
            }
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/{tripId}/expense")
    public ResponseEntity<Void> addExpense(@PathVariable String tripId,
                                           @RequestHeader(name = "Authorization") String token,
                                           @RequestBody ExpenseDto dto) {
        User user = getUser(token);
        Expense expense = new Expense(dto, user);

        Trip trip = Database.trips.get(tripId);
        if (trip == null) {
            trip = new Trip();
            Database.trips.put(tripId, trip);
        }

        if (!trip.getActive()) {
            throw new RuntimeException("Trip was closed");
        }

        trip.getExpenses().add(expense);
        return ResponseEntity.status(200).build();
    }


    @GetMapping("/{tripId}")
    public TripDto getTrip(@PathVariable String tripId, @RequestHeader(name = "Authorization") String token) {
        verifyToken(token);
        Trip trip = getTripById(tripId);
        return new TripDto(trip);
    }

    @GetMapping("/{tripId}/summary")
    public TripSummaryDto getTripSummary(@PathVariable String tripId, @RequestHeader(name = "Authorization") String token) {
        verifyToken(token);
        Trip trip = getTripById(tripId);
        return new TripSummaryDto(trip);
    }

    @GetMapping("/")
    public Set<String> getTrips(@RequestHeader(name = "Authorization") String token) {
        verifyToken(token);
        return Database.trips.keySet();
    }

    @PostMapping("/{tripId}/close")
    public ResponseEntity<Void> closeTrip(@PathVariable String tripId, @RequestHeader(name = "Authorization") String token) {
        verifyToken(token);
        Trip trip = getTripById(tripId);
        trip.setActive(false);
        return ResponseEntity.status(200).build();
    }

    private Trip getTripById(String tripId) {
        Trip trip = Database.trips.get(tripId);
        if (trip == null) {
            throw new ObjectNotFoundException("Could not find trip");
        }
        return trip;
    }

    private User getUser(String token) {
        Claims claims = null;
        try {
            claims = JWTIssuer.decodeJWT(token.split(" ")[1]);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Expired token");
        }
        String subClaim = claims.get("sub", String.class);
        User user = getUserByUsername(subClaim);

        return user;
    }

    private void verifyToken(String token) {
        User user = getUser(token);
        if (user == null) {
            throw new RuntimeException("Invalid user");
        }
    }

    private User getUserByUsername(String username) {
        for (User u : Database.users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

}
