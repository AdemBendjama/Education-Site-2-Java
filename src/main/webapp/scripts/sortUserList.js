let sortButton = document.getElementById("sortButton");


sortButton.onchange = function () {
    //
    let sortForm = document.getElementById("sort-form");
    let sortBy = document.getElementById("sortBy");
    let sortSubmit = document.getElementById("sort-submit");
    console.log(sortForm.id);

    switch (sortButton.value) {
        // Sort By EMAIL
        case "email" : {
            sortBy.value = "email";
            sortSubmit.click();

            break;
        }
        // Sort By NAME
        case "name" : {
            sortBy.value = "name";
            sortSubmit.click();

            break;
        }
        // Sort By RANK
        case "rank" : {
            sortBy.value = "rank";
            sortSubmit.click();

            break;
        }
    }
}
