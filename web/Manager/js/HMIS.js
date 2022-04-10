var sow00messages = function () {
    $('.sh-mainpanel').load('00messages.html', null, function () {
    });
    return false;
};
var sow03newExam = function () {
    $('.sh-mainpanel').load('03newExam.html', null, function () {
    });
    return false;
};
var show02oneFormChart = function () {
    $('.sh-mainpanel').load('02oneFormChart.html', null, function () {
    });
    return false;
};
var show02newForm = function () {
    $('.sh-mainpanel').load('02newForm.html', null, function () {
    });
    return false;
};
var show02oneCompletedForm = function () {
    $('.sh-mainpanel').load('02oneCompletedForm.html', null, function () {
    });
    return false;
};

var show02oneFormToComplete = function () {
    $('.sh-mainpanel').load('02oneFormToComplete.html', null, function () {
    });
    return false;
};
var sow03oneExam = function () {
    $('.sh-mainpanel').load('03oneExam.html', null, function () {
    });
    return false;
};
var show01TextEditor = function () {
    $('.sh-mainpanel').load('01textEditor.html', null, function () {
    });
    return false;
};
var sow04onePlanToAssess = function () {
    $('.sh-mainpanel').load('04onePlanToAssess.html', null, function () {
    });
    return false;
};
var sow04newPlan = function () {
    $('.sh-mainpanel').load('04newPlan.html', null, function () {
    });
    return false;
};
                        
var sow04oneStepToAssess = function () {
    $('.sh-mainpanel').load('04onePlanToAssess.html', null, function () {
    });
    return false;
};
var sاow04newAssess = function () {
    $('.sh-mainpanel').load('04newAssess.html', null, function () {
    });
    return false;
};
var show05commettes = function () {
    $('.sh-mainpanel').load('05commettes.html', null, function () {
    });
    return false;
};
var show05newCommette = function () {
    $('.sh-mainpanel').load('05newCommette.html', null, function () {
    });
    return false;
};
var show05inviteCommete = function () {
    $('.sh-mainpanel').load('05inviteCommete.html', null, function () {
    });
    return false;
};
var show05sessions = function () {
    $('.sh-mainpanel').load('05sessions.html', null, function () {
    });
    return false;
};
        
var show05OneSession = function () {
    $('.sh-mainpanel').load('05OneSession.html', null, function () {
    });
    return false;
};
var show06newIndicator = function () {
    $('.sh-mainpanel').load('06newIndicator.html', null, function () {
    });
    return false;
};
var show07newIndicator = function () {
    $('.sh-mainpanel').load('07newStandard.html', null, function () {
    });
    return false;
};
var show07uploadNewFile = function () {
    $('.sh-mainpanel').load('07uploadNewFile.html', null, function () {
    });
    return false;
};
var show07otherDocumentUpload = function () {
    $('.sh-mainpanel').load('07otherDocumentUpload.html', null, function () {
    });
    return false;
};
var show07newDocumets = function () {
    $('.sh-mainpanel').load('07newDocumets.html', null, function () {
    });
    return false;
};
var show08brainStorms= function () {
    $('.sh-mainpanel').load('08brainStorms.html', null, function () {
    });
    return false;
};
var show08completeOneBS= function () {
    $('.sh-mainpanel').load('08completeOneBS.html', null, function () {
    });
    return false;
};
var show08brainStormResults= function () {
    $('.sh-mainpanel').load('08brainStormResults.html', null, function () {
    });
    return false;
};
                
var show08newBreainStorm= function () {
    $('.sh-mainpanel').load('08newBreainStorm.html', null, function () {
    });
    return false;
};

function printDiv(selector) 
{
  var divToPrint=document.getElementById(selector);
  var newWin=window.open('','Print-Window');
  newWin.document.open();
  newWin.document.write('<html><link rel="stylesheet" href="Manager/shamcey.css"><body onload="window.print()">'+divToPrint.innerHTML+'</body></html>');
  newWin.document.close();
  setTimeout(function(){newWin.close();},10);

}


