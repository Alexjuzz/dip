Web User Controller 
- **GET `/register`** - Показывает форму регистрации пользователя.
- **POST `/register`** - Регистрирует пользователя и перенаправляет на выбор лодки.
- **GET `/login`** - Показывает форму входа.
- **POST `/login`** - Аутентифицирует пользователя и переходит на страницу бронирования или показывает ошибку.

- User Controller 
- - **POST (interface) `createUser`** - Создает нового пользователя.
- **GET (interface) `getAllUsers`** - Получает всех пользователей.
- **GET (interface) `getByNumber`** - Получает пользователя по его номеру телефона.

Web Booking Controller 
- **GET `/booking/process-booking`** - Обрабатывает выбор места для бронирования и перенаправляет на форму бронирования.
- **POST `/booking/{seatId}`** - Устанавливает бронирование на место.
- **GET `/booking/form`** - Показывает форму бронирования с предварительно выбранным местом и временем.
Booking Controller
- **GET (interface) `getBookingReservationBySeatId`** - Получает бронирования по ID места.
- **POST (interface) `setBookingToPlace`** - Бронирует место на определенное время.
- **POST (interface) `changeReservedBookingTime`** - Изменяет время существующего бронирования.
- **POST (interface) `cancelReservation`** - Отменяет бронирование.
Web Boat Controller
- **GET `/boats/selection`** - Показывает форму выбора лодки.
- **GET `/boats/process-selection`** - Обрабатывает выбор лодки и перенаправляет на детали лодки.
- **GET `/boats/details/{boatId}`** - Показывает детали выбранной лодки вместе с доступными местами и временем.
Boat Controller
- **POST (interface) `createBoat`** - Создает новую лодку.
- **GET (interface) `getAll`** - Получает все лодки.
- **GET (interface) `getSeatsById`** - Получает места по ID лодки.
- **GET (interface) `getBoatById`** - Получает лодку по ее ID.
- **POST (interface) `setPlacesToBoat`** - Устанавливает вместимость для лодки.
- **POST (interface) `setNameToBoat`** - Переименовывает лодку.
- **POST (interface) `setTripToBoat`** - Устанавливает тип поездки для лодки.
Facade Controller
- **GET `/api/users`** - Получает всех пользователей.
- **GET `/api/user/{phoneNumber}`** - Получает пользователя по номеру телефона.
- **POST `/api/user/create`** - Создает нового пользователя.
- **GET `/api/book/{seatId}`** - Получает бронирования по ID места.
- **POST `/api/book/set/book-place`** - Бронирует место на определенное время.
- **POST `/api/book/change/time`** - Изменяет время бронирования.
- **POST `/api/book/cancel`** - Отменяет бронирование.
- **GET `/api/boats`** - Получает все лодки.
- **GET `/api/boat/{id}`** - Получает лодку по ID.
- **GET `/api/boat/seats/{boatId}`** - Получает места по ID лодки.

