
Here's a summary of the API endpoints available across the various controllers you provided, organized by the controller. This summary includes the HTTP method, route, and a brief description of each endpoint's functionality.
Web User Controller (WebUserController.java)
GET /register: Shows the user registration form.
POST /register: Registers a user and redirects to boat selection.
GET /login: Shows the login form.
POST /login: Authenticates a user and navigates to the booking page or shows an error.
User Controller (UserController.java)
POST (interface) createUser: Creates a new user.
GET (interface) getAllUsers: Retrieves all users.
GET (interface) getByNumber: Fetches a user by their phone number.
Web Booking Controller (WebBookingController.java)
GET /booking/process-booking: Processes seat selection for booking and redirects to booking form.
POST /booking/{seatId}: Sets a booking for a seat.
GET /booking/form: Shows the booking form with pre-selected seat and time.
Booking Controller (BookingController.java)
GET (interface) getBookingReservationBySeatId: Retrieves booking reservations by seat ID.
POST (interface) setBookingToPlace: Books a seat for a specific time.
POST (interface) changeReservedBookingTime: Changes the time of an existing booking.
POST (interface) cancelReservation: Cancels a reservation.
Web Boat Controller (WebBoatController.java)
GET /boats/selection: Shows the boat selection form.
GET /boats/process-selection: Processes boat selection and redirects to boat details.
GET /boats/details/{boatId}: Shows details of a selected boat along with available seats and times.
Boat Controller (BoatController.java)
POST (interface) createBoat: Creates a new boat.
GET (interface) getAll: Retrieves all boats.
GET (interface) getSeatsById: Fetches seats by boat ID.
GET (interface) getBoatById: Retrieves a boat by its ID.
POST (interface) setPlacesToBoat: Sets the capacity for a boat.
POST (interface) setNameToBoat: Renames a boat.
POST (interface) setTripToBoat: Sets the trip type for a boat.
Facade Controller (FacadeController.java)
GET /api/users: Retrieves all users.
GET /api/user/{phoneNumber}: Fetches a user by phone number.
POST /api/user/create: Creates a new user.
GET /api/book/{seatId}: Gets booking reservations by seat ID.
POST /api/book/set/book-place: Books a seat for a specific time.
POST /api/book/change/time: Changes the booking time for a reservation.
POST /api/book/cancel: Cancels a booking.
GET /api/boats: Retrieves all boats.
GET /api/boat/{id}: Fetches a boat by ID.
GET /api/boat/seats/{boatId}: Gets seats by boat ID.
These summaries offer an overview of the available functionalities within your application regarding user management, booking processes, and boat selection. Each endpoint supports different aspects of the application's overall flow, from user registration and authentication to booking management and boat selection.
Вот сводка доступных API-эндпоинтов из предоставленных вами контроллеров, организованных по контроллерам. Эта сводка включает в себя HTTP метод, маршрут и краткое описание функциональности каждого эндпоинта.

Контроллер Веб-Пользователя (WebUserController.java)
GET /register: Показывает форму регистрации пользователя.
POST /register: Регистрирует пользователя и перенаправляет на выбор лодки.
GET /login: Показывает форму входа.
POST /login: Аутентифицирует пользователя и переходит на страницу бронирования или показывает ошибку.
Контроллер Пользователя (UserController.java)
POST (интерфейс) createUser: Создает нового пользователя.
GET (интерфейс) getAllUsers: Получает всех пользователей.
GET (интерфейс) getByNumber: Получает пользователя по его номеру телефона.
Контроллер Веб-Бронирования (WebBookingController.java)
GET /booking/process-booking: Обрабатывает выбор места для бронирования и перенаправляет на форму бронирования.
POST /booking/{seatId}: Устанавливает бронирование на место.
GET /booking/form: Показывает форму бронирования с предварительно выбранным местом и временем.
Контроллер Бронирования (BookingController.java)
GET (интерфейс) getBookingReservationBySeatId: Получает бронирования по ID места.
POST (интерфейс) setBookingToPlace: Бронирует место на определенное время.
POST (интерфейс) changeReservedBookingTime: Изменяет время существующего бронирования.
POST (интерфейс) cancelReservation: Отменяет бронирование.
Контроллер Веб-Лодок (WebBoatController.java)
GET /boats/selection: Показывает форму выбора лодки.
GET /boats/process-selection: Обрабатывает выбор лодки и перенаправляет на детали лодки.
GET /boats/details/{boatId}: Показывает детали выбранной лодки вместе с доступными местами и временем.
Контроллер Лодок (BoatController.java)
POST (интерфейс) createBoat: Создает новую лодку.
GET (интерфейс) getAll: Получает все лодки.
GET (интерфейс) getSeatsById: Получает места по ID лодки.
GET (интерфейс) getBoatById: Получает лодку по ее ID.
POST (интерфейс) setPlacesToBoat: Устанавливает вместимость для лодки.
POST (интерфейс) setNameToBoat: Переименовывает лодку.
POST (интерфейс) setTripToBoat: Устанавливает тип поездки для лодки.
Фасадный Контроллер (FacadeController.java)
GET /api/users: Получает всех пользователей.
GET /api/user/{phoneNumber}: Получает пользователя по номеру телефона.
POST /api/user/create: Создает нового пользователя.
GET /api/book/{seatId}: Получает бронирования по ID места.
POST /api/book/set/book-place: Бронирует место на определенное время.
POST /api/book/change/time: Изменяет время бронирования.
POST /api/book/cancel: Отменяет бронирование.
GET /api/boats: Получает все лодки.
GET /api/boat/{id}: Получает лодку по ID.
GET /api/boat/seats/{boatId}: Получает места по ID лодки.
Эти сводки предоставляют обзор доступных функциональных возможностей вашего приложения по управлению пользователями, процессам бронирования и выбору лодок. Каждый эндпоинт поддерживает различные аспекты общего потока работы приложения, от регистрации и аутентификации пользователя до управления бронированием и выбора лодок.
