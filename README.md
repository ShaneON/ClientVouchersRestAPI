##ClientVouchers

The app was developed with android studio. I used the retrofit library to send requests to the api. 

The UI has two fields for searching, one for entering email and one for phone. 
Under those is a field for entering the amount on the voucher and a button to create it.

For some reason the branchId's value wouldn't take to its field in the object so I wasn't able to use it.

One problem with the app is that if you search for an email or phone number thats not in the api it will crash, because of how retrofit handles empty responses. I didnt have time to fix this.

The apk is available above for download (clientvouchers.apk)