Android Boxers
=======

Prevent accidental exposure of private members.

---

### What is this?

Depending on your implementation of Model-View-Presenter, you might choose to store state in the
Presenter instead of the View. One of the problems with this approach on Android is that data
is often passed to your UI component _through_ the View - i.e. passing intents/bundles to
Activities/Fragments - and this data must later be manually extracted and handed to the Presenter.
Because the unpackaging of this data happens in the View it may be tempting to access it directly
rather than interfacing with the Presenter. This can lead to subtle bugs and unexpected state.

Android Boxers is an experiment in using Kotlin's delegation features to better hide this data
from the View while making it easier to propogate data to the Presenter in a type-safe manner.
Data is transported in 'Boxes' which are attached to some sort of Boxer. Boxers are responsible
for storing and retrieving values. Currently there are only two Boxers - BundleBoxer and
MapBoxer - but in theory a Boxer could be backed by any type of key-value storage mechanism
including SharedPreferences.

---

### Should I use this?

No. It's just an experiment.

---

### But if I _were_ to use this, how would I go about it?

Make a Box class for your data, using `Box` or `NBox` delegates for the fields.
```kotlin
class SubmissionListBox : Box() {
    var someMessage by BoxString() // Default is empty String
    var anotherMessage by BoxString("Hello, world") // Default is 'Hello, world'
    var nullableMessage by NBoxString() // Nullable; Default is null
    var selectionIdx by BoxInt() // Default value is 0
    var gradeAnonymously by BoxBoolean() // Default value is false
    var students by BoxParcelableList<User>() // Default value is empty list
    var submissions by NBoxParcelableList<Submission>() // Default value is null
    var course by BoxParcelable(Course()) // Default value is a blank course
    var assignment by NBoxParcelable() // Default value is null
}
```

Set up your View/Presenter to use the Box:
```kotlin
class SubmissionListFragment : Fragment(), SubmissionListView {
    fun createPresenter() = SubmissionListPresenter(SubmissionListBox().wrap(this))
    
    override fun onResume() {
        super.onResume()
        presenter.loadData()
    }

    override fun showMessage(message: String) {
        shortToast(message)
    }

    companion object {
        fun newInstance(box: SubmissionListBox) = SubmissionListFragment().applyBox(box)
    }  
}

class SubmissionListPresenter(box: SubmissionListBox) : Presenter() {
    fun loadData() {
        // Easily access box data
        print("Message is: ${box.someMessage}")
        print("Selection is: ${box.selectionIdx}")
        print("Grading anonymously: ${box.gradeAnonymously}")
        view?.showMessage(box.anotherMessage)
    }
}

```

Create an instance of your box, add some or all of the data, and pass it to the View:
```kotlin
val box = SubmissionListBox().apply {
    someMessage = "This is a message"
    selectionIdx = 5
    gradeAnonymously = true
}

SubmissionListFragment.newInstance(box)

```


---


License
-------

    Copyright 2017 Instructure, Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.