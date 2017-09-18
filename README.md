# revl

### core
The core elements of the application, chiefly the model objects.

### app
Abstractions of logic called "interactors" or sometimes "use cases", and abstractions of data 
sources. These two concepts work in concert to define the core logic of the application.

### data
Platform agnostic implementations of data source interfaces defined in the `app` module.

### view
Virtual (redux) implementation of the view state. This layer is a boundary between the model and the 
UI layers.

### android
Android-based delivery mechanism for the layers below. Platform specific implementations of data
sources and other dependencies are defined here. Analogous modules could exist as siblings to this 
module for other domains, e.g. `desktop`, `web`, `cli`. 
