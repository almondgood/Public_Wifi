var eventTarget = document.getElementsByClassName('btn_delete')


for (var i = 0; i < eventTarget.length; i++) {
    eventTarget[i].addEventListener('click', function () {
        var parent = document.querySelector('.tg tbody')
        parent.removeChild(this.parentElement.parentElement)
        i--
    })
}
