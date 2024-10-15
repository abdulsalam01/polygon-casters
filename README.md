by Abdul Salam (abdulsalam01)

# Introduction

API gateway to detect the `Ships` coordinates which addressing as 'enter' or 'leave' a corresponding `Ports` from external-sources party in Rotterdam and expose the information in JSON format.

List of defined ports:
```
[
   [4.09365, 51.98509],
   [4.08719, 52.01616],
   [3.98969, 52.0345],
   [3.94652, 51.99088],
   [3.95805, 51.9598],
   [3.98431, 51.91666],
   [4.46901, 51.82003],

   [4.55084, 51.64443],
   [4.629, 51.664],
   [4.69875, 51.83797],
   [4.5382, 51.91703],
   [4.09365, 51.98509]
]
```

## Goals

Create a backend API which will detect ships entering and leaving the port of Rotterdam, and expose this information. A suitable interval is polling the actual ship locations once per minute.

The developed backend should have an endpoint where one can fetch all detected port enter/leave events and a second endpoint providing a list with all ships that are currently in the port defined of Rotterdam.

## Prerequisite

1. Java, JDK and JVM are installed in your machine for run and compile the code.
2. Maven as depedency manager is installed.
3. Credentials of the external-party API to access the sources, you have to create a `Credentials.java` inside `src/main/com/example/constants`, looks like this:

```
public class Credentials {
    public static final String USERNAME = "your-email";
    public static final String PASSWORD = "your-password";
}
```

4. Change the values in this file `src/main/com/example/constants/Environments.java`, for determining the process of:
   - Get ships from actual external resources or manual seeder controlled by `IS_TESTMODE_ACTIVE`
   - Scheduler want to be on or off that controlled by `IS_SCHEDULER_ACTIVE`

## Tech Stack
- Java as main language
- Spring as API framework
- Fasterxml Jackson as JSON converter and data stream

## Approach
There are severals approach implemented for handling and detects ship movements and coordinates, such as:
- Async method combined with Stream process when get data from external-party sources (`/ships` endpoint) to minimize I/O process caused by big data responses.
- Ray Casting Algorithm to detect if points/ships inside the given of polygon area, references from this: [Ray Casting Algorithm](https://medium.com/@girishajmera/exploring-algorithms-to-determine-points-inside-or-outside-a-polygon-038952946f87)
- Memory storage for keep data that already being fetched from external-party, to minimize the latency and increase performance
- Make a Scheduler that run every 1 minute to fetch the current ship and store all of events in memory storage as `Set`

## Endpoints
| Method | URI | Response | Description | 
| :---:  | :---: | :---:    | | :---:    |
| POST | `/auth/login`| `AuthManager.java` entity | Authentication request to 3rd party using `Credentials.java` |
| GET | `/ship` | `ShipManager.java` entity | Get list of all ships available |
| GET | `/port/ship` | Array Set of `MMSI ID` | Get the current ships that already in port/polygon
| GET | `/port/event` | Array Set of `MMSI ID` and `Event` | Get the ships within event status about which ship is `ENTER/LEAVE`

## Improvements
- For handling a large data from external endpoints, we have to consider for saving the fetched data to own datastore and then get from own data store by chunking, limiting and offsetting to reduces the latency of display all data.

- We also can use `token bucket algorithm / throttling concept` for fetching a big data in client-side.

- Use a memory cache datastore within `LIST` data structure to only save the `MMSI ID` for looking up to the datastore, but make sure `MMSI ID` already being indexed in datastore.

- Use paralelism concept to processing data, instead of 1 pipe, use several pipe and merged together the processed data.

by Abdul Salam (abdulsalam01)
